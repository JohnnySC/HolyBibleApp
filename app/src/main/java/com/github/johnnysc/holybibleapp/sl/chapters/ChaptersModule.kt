package com.github.johnnysc.holybibleapp.sl.chapters

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.domain.chapters.BaseChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.chapters.BaseChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersInteractor
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersCommunication
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersViewModel
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 15.07.2021
 **/
class ChaptersModule(
    private val coreModule: CoreModule,
    private val booksRepository: BooksRepository,
    private val repository: ChaptersRepository,
    private val clearChapters: () -> Unit
) : BaseModule<ChaptersViewModel> {

    override fun viewModel() = ChaptersViewModel(
        interactor(),
        communication(),
        mapper(),
        coreModule.navigator,
        coreModule.chapterCache,
        coreModule.navigationCommunication,
        coreModule.resourceProvider,
        clearChapters
    )

    private fun interactor() = ChaptersInteractor.Base(
        repository,
        BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper()),
        booksRepository,
        coreModule.bookCache,
        coreModule.scrollPositionCache
    )

    private fun communication() = ChaptersCommunication.Base()

    private fun mapper() = BaseChaptersDomainToUiMapper(
        BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(coreModule.resourceProvider)),
        coreModule.resourceProvider
    )
}