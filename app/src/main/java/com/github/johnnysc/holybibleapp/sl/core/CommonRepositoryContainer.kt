package com.github.johnnysc.holybibleapp.sl.core

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.sl.books.BooksRepositoryProvider
import com.github.johnnysc.holybibleapp.sl.books.ClearBooks
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersRepositoryProvider
import com.github.johnnysc.holybibleapp.sl.chapters.ClearChapters

/**
 * @author Asatryan on 30.07.2021
 **/
class CommonRepositoryContainer(
    private val books: () -> BooksRepository,
    private val chapters: () -> ChaptersRepository
) : BooksRepositoryProvider, ChaptersRepositoryProvider, ClearBooks, ClearChapters {

    private var booksRepository: BooksRepository? = null
    private var chaptersRepository: ChaptersRepository? = null

    override fun booksRepository(): BooksRepository {
        if (booksRepository == null)
            booksRepository = books()
        return booksRepository!!
    }

    override fun clearBooksRepository() {
        booksRepository = null
    }

    override fun chaptersRepository(): ChaptersRepository {
        if (chaptersRepository == null)
            chaptersRepository = chapters()
        return chaptersRepository!!
    }

    override fun clearChaptersRepository() {
        chaptersRepository = null
    }
}