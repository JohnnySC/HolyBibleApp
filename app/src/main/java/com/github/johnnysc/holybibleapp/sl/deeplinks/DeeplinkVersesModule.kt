package com.github.johnnysc.holybibleapp.sl.deeplinks

import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.data.verse.VerseIdCache
import com.github.johnnysc.holybibleapp.domain.books.BooksRepository
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.presentation.books.BookCache
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterCache
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkVersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkVersesViewModel
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.verses.VersesModule

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkVersesModule(
    coreModule: CoreModule,
    booksRepository: BooksRepository,
    chaptersRepository: ChaptersRepository,
    bookCache: BookCache,
    useMocks: Boolean,
    booksRu: () -> List<BookRu>
) : VersesModule(
    coreModule,
    booksRepository,
    chaptersRepository,
    useMocks,
    bookCache,
    ChapterCache.Deeplink(coreModule.resourceProvider),
    booksRu
) {

    override fun mapper() = DeeplinkVersesDomainToUiMapper(
        VerseIdCache.Deeplink(coreModule.resourceProvider),
        BaseVerseDomainToUiMapper(coreModule.resourceProvider),
        coreModule.resourceProvider
    )

    override fun viewModel() = DeeplinkVersesViewModel(
        coreModule.navigator,
        interactor(),
        communications(),
        mapper(),
        coreModule.deeplinkData,
        coreModule.resourceProvider,
        coreModule.multiply
    )
}