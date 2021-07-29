package com.github.johnnysc.holybibleapp.presentation.languages

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication

/**
 * @author Asatryan on 18.07.2021
 **/
class LanguagesViewModel(
    private val communication: LanguagesCommunication,
    private val language: Language,
    private val navigationCommunication: NavigationCommunication,
    private val navigator: LanguagesNavigator,
    private val resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    override fun getTitleResId() = R.string.choose_language

    fun observeLanguage(owner: LifecycleOwner, observer: Observer<LanguagesUi>) =
        communication.observe(owner, observer)

    fun chooseEnglish() {
        language.chooseEnglish()
        navigate()
    }

    fun chooseRussian() {
        language.chooseRussian()
        navigate()
    }

    fun init() {
        val choice = when {
            language.isChosenRussian() -> LanguageChoice.RUSSIAN
            language.isChosenEnglish() -> LanguageChoice.ENGLISH
            else -> LanguageChoice.NONE
        }
        communication.map(
            LanguagesUi.Base(
                choice,
                resourceProvider.getString(R.string.english),
                resourceProvider.getString(R.string.russian)
            )
        )
    }

    fun showBackIcon(): Boolean = with(navigator) {
        saveLanguagesScreen()
        return canGoBack()
    }

    private fun navigate() = navigationCommunication.map(navigator.nextFromLanguages())
}