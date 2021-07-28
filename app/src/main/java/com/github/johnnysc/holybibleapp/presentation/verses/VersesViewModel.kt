package com.github.johnnysc.holybibleapp.presentation.verses

import androidx.lifecycle.*
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import kotlinx.coroutines.Dispatchers

import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesViewModel(
    private val navigator: VersesNavigator,
    private val interactor: VersesInteractor,
    private val communication: VersesCommunication,
    private val mapper: VersesDomainToUiMapper<VersesUi>,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider) {

    fun fetchVerses() {
        communication.map(VersesUi.Base(listOf(VerseUi.Progress), getTitle()))
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchVerses()
            val ui = list.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(ui)
            }
        }
    }

    fun observeVerses(owner: LifecycleOwner, observer: Observer<VersesUi>) {
        communication.observe(owner, observer)
    }

    fun init() {
        navigator.saveVersesScreen()
        fetchVerses()
    }
}