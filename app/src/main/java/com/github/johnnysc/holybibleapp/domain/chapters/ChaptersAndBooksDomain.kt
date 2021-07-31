package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.TextMapper
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksDomain

/**
 * todo unit test
 * @author Asatryan on 24.07.2021
 **/
class ChaptersAndBooksDomain(
    private val chapters: ChaptersData,
    private val books: BooksData,
    private val bookId: Read<Int>
) : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper<ChaptersDomain>> {

    private val empty by lazy {
        object : Abstract.Object<Unit, TextMapper> {
            override fun map(mapper: TextMapper) = Unit
        }
    }

    override fun map(mapper: ChaptersDataToDomainMapper<ChaptersDomain>) = when {
        books is BooksData.Success && chapters is ChaptersData.Success ->
            chapters.map(mapper, books.byId(bookId.read()))

        chapters is ChaptersData.Fail -> chapters.map(mapper, empty)

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