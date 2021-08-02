package com.github.johnnysc.holybibleapp.sl.core

import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.sl.books.BooksModule
import com.github.johnnysc.holybibleapp.sl.books.BooksRepositoryContainer
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersModule
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersRepositoryContainer
import com.github.johnnysc.holybibleapp.sl.languages.LanguagesModule
import com.github.johnnysc.holybibleapp.sl.verses.VersesModule

/**
 * @author Asatryan on 30.07.2021
 **/
interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule, private val useMocks: Boolean) :
        DependencyContainer {

        private val commonRepositoryContainer = CommonRepositoryContainer(
            ::booksRepository,
            ::chaptersRepository,
            ::russianBooks
        )

        override fun module(feature: Feature) = when (feature) {
            Feature.MAIN -> coreModule
            Feature.LANGUAGES -> LanguagesModule(coreModule, commonRepositoryContainer)
            Feature.BOOKS -> BooksModule(
                coreModule,
                useMocks,
                commonRepositoryContainer.booksRepository()
            ) {
                commonRepositoryContainer.clearBooksRepository()
            }
            Feature.CHAPTERS -> ChaptersModule(
                coreModule,
                commonRepositoryContainer.booksRepository(),
                commonRepositoryContainer.chaptersRepository()
            ) {
                commonRepositoryContainer.clearChaptersRepository()
            }
            Feature.VERSES -> VersesModule(
                coreModule,
                commonRepositoryContainer.booksRepository(),
                commonRepositoryContainer.chaptersRepository(),
                useMocks
            ) {
                commonRepositoryContainer.booksRu()
            }
        }

        private fun booksRepository(booksRu: () -> List<BookRu>) =
            BooksRepositoryContainer(coreModule, useMocks, booksRu).repository()

        private fun chaptersRepository(booksRu: () -> List<BookRu>) =
            ChaptersRepositoryContainer(coreModule, useMocks, booksRu).repository()

        private fun russianBooks() = RussianBooksContainer(coreModule).booksRu()
    }
}