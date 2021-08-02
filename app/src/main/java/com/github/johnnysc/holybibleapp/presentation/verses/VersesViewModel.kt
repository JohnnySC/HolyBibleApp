package com.github.johnnysc.holybibleapp.presentation.verses

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
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
) : BaseViewModel(resourceProvider), ChangeFavorite<Int> {

    fun fetchVerses() {
        communication.map(VersesUi.Base(ArrayList(listOf(VerseUi.Progress)), title()))
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

    override fun scrollPosition() = interactor.scrollPosition()
    override fun saveScrollPosition(position: Int) = interactor.saveScrollPosition(position)

    fun showNextChapterVerses() {
        interactor.showNextChapter()
        fetchVerses()
    }

    override fun changeFavorite(id: Int) {
        super.changeFavorite(id, communication, interactor)
    }
}