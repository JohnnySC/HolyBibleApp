package com.github.johnnysc.holybibleapp.presentation.chapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.core.Show
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
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider), Show {

    fun observeChapters(owner: LifecycleOwner, observer: Observer<Pair<List<ChapterUi>, String>>) {
        chaptersCommunication.observe(owner, observer)
    }

    fun fetchChapters() {
        chaptersCommunication.map(Pair(listOf(ChapterUi.Progress), getTitle()))
        viewModelScope.launch(Dispatchers.IO) {
            val chapters = chaptersInteractor.fetchChapters()
            val chaptersUi = chapters.map(chaptersMapper)
            withContext(Dispatchers.Main) {
                chaptersUi.map(chaptersCommunication)
            }
        }
    }

    fun init() {
        navigator.saveChaptersScreen()
        fetchChapters()
    }

    override fun open(id: Int) {
        chapterCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }
}