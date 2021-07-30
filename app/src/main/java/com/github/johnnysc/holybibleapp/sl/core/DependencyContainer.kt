package com.github.johnnysc.holybibleapp.sl.core

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
            ::chaptersRepository
        )

        override fun module(feature: Feature) = when (feature) {
            Feature.MAIN -> coreModule
            Feature.LANGUAGES -> LanguagesModule(coreModule)
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
            )
        }

        private fun booksRepository() =
            BooksRepositoryContainer(coreModule, useMocks).repository()

        private fun chaptersRepository() =
            ChaptersRepositoryContainer(coreModule, useMocks).repository()
    }
}