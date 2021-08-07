package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.domain.books.BooksDataMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper

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
            chapters.map(mapper, books.map(BooksDataMapper.Id(bookId)))
        chapters is ChaptersData.Fail -> chapters.map(mapper, BookData.Empty())
        else -> ChaptersDomain.Fail(books.map(BooksDataMapper.Error()))
    }
}