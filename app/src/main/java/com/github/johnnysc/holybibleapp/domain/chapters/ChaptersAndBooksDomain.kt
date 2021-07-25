package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksDomain

/**
 * @author Asatryan on 24.07.2021
 **/
class ChaptersAndBooksDomain(
    private val chapters: ChaptersData,
    private val books: BooksData,
    private val bookId: Read<Int>
) : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper<ChaptersDomain>> {

    override fun map(mapper: ChaptersDataToDomainMapper<ChaptersDomain>) = when {
        books is BooksData.Success && chapters is ChaptersData.Success ->
            chapters.map(mapper, books.getById(bookId.read()).name())

        chapters is ChaptersData.Fail -> chapters.map(mapper, "")

        else -> {
            var errorType: ErrorType = ErrorType.GENERIC_ERROR
            books.map(object : BooksDataToDomainMapper<BooksDomain>() {
                override fun map(data: List<BookData>) = BooksDomain.Fail(errorType)
                override fun map(e: Exception): BooksDomain {
                    errorType = errorType(e)
                    return BooksDomain.Fail(errorType)
                }
            })
            ChaptersDomain.Fail(errorType)
        }
    }
}