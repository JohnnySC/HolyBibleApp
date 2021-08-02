package com.github.johnnysc.holybibleapp.sl.core

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.sl.books.BooksRepositoryProvider
import com.github.johnnysc.holybibleapp.sl.books.ClearBooks
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersRepositoryProvider
import com.github.johnnysc.holybibleapp.sl.chapters.ClearChapters

/**
 * @author Asatryan on 30.07.2021
 **/
class CommonRepositoryContainer(
    private val books: (() -> List<BookRu>) -> BooksRepository,
    private val chapters: (() -> List<BookRu>) -> ChaptersRepository,
    private val russianBooks: () -> List<BookRu>
) : BooksRepositoryProvider, ChaptersRepositoryProvider, ClearBooks, ClearChapters,
    BooksRuProvider, ClearRussianBooks {

    private var booksRepository: BooksRepository? = null
    private var chaptersRepository: ChaptersRepository? = null
    private val booksRussian: ArrayList<BookRu> = ArrayList()

    override fun booksRepository(): BooksRepository {
        if (booksRepository == null)
            booksRepository = books { booksRu() }
        return booksRepository!!
    }

    override fun clearBooksRepository() {
        booksRepository = null
    }

    override fun chaptersRepository(): ChaptersRepository {
        if (chaptersRepository == null)
            chaptersRepository = chapters { booksRu() }
        return chaptersRepository!!
    }

    override fun clearChaptersRepository() {
        chaptersRepository = null
    }

    override fun booksRu(): List<BookRu> {
        if (booksRussian.isEmpty())
            booksRussian.addAll(russianBooks())
        return booksRussian
    }

    override fun clearBooksRu() {
        booksRussian.clear()
    }
}