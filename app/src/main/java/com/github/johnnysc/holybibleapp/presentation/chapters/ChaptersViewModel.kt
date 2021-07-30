package com.github.johnnysc.holybibleapp.presentation.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersInteractor
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 13.07.2021
 **/
class ChaptersViewModel(
    private val chaptersInteractor: ChaptersInteractor,
    private val chaptersCommunication: ChaptersCommunication,
    private val chaptersMapper: ChaptersDomainToUiMapper<ChaptersUi>,
    private val navigator: ChaptersNavigator,
    private val chapterCache: Save<Int>,
    private val navigationCommunication: NavigationCommunication,
    resourceProvider: ResourceProvider,
    private val clearChapters: () -> Unit
) : BaseViewModel(resourceProvider), Show {

    private var clear = true
    private val bookNameProgress = object : Abstract.Object<Unit, TextMapper> {
        override fun map(mapper: TextMapper) = mapper.map(title())
    }

    fun observeChapters(owner: LifecycleOwner, observer: Observer<ChaptersUi>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(
            ChaptersUi.Base(
                listOf(ChapterUi.Progress),
                bookNameProgress
            )
        )
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.fetchChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersCommunication.map(chaptersUi)
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    override fun open(id: Int) {
        chapterCache.save(id)
        clear = false
        navigator.nextScreen(navigationCommunication)
    }

    override fun scrollPosition() = chaptersInteractor.scrollPosition()
    override fun saveScrollPosition(position: Int) =
        chaptersInteractor.saveScrollPosition(position)

    override fun onCleared() {
        super.onCleared()
        if (clear) clearChapters()
    }
}