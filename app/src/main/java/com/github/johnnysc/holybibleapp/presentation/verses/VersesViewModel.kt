package com.github.johnnysc.holybibleapp.presentation.verses

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesInteractor
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
    private val bookCache: Read<Pair<Int, String>>,
    private val chapterCache: Read<Int>
) : ViewModel() {
    fun getTitle() = "${bookCache.read().second} Ch.${chapterCache.read()}"

    fun fetchVerses() {
        communication.map(listOf(VerseUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchVerses()
            val uiList = list.map(mapper)
            withContext(Dispatchers.Main) {
                uiList.map(communication)
            }
        }
    }

    fun observeVerses(owner: LifecycleOwner, observer: Observer<List<VerseUi>>) {
        communication.observe(owner, observer)
    }

    fun init() {
        navigator.saveVersesScreen()
        fetchVerses()
    }
}