package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.domain.books.BooksDataMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.data.verses.VersesData
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ErrorChaptersDataToDomainMapper

/**
 * @author Asatryan on 24.07.2021
 **/
class VersesAndBooksDomain(
    private val verses: VersesData,
    private val books: BooksData,
    private val chapters: ChaptersData,
    private val bookId: Read<Int>,
    private val chapterNumber: Read<Int>
) : Abstract.Object<VersesDomain, VersesDataToDomainMapper<VersesDomain>> {
    override fun map(mapper: VersesDataToDomainMapper<VersesDomain>) = when {
        books is BooksData.Success && verses is VersesData.Success && chapters is ChaptersData.Success -> {
            val chapterId = chapterNumber.read()
            verses.map(
                mapper,
                books.map(BooksDataMapper.Id(bookId)),
                chapterId,
                chapters.matches(chapterId)
            )
        }
        verses is VersesData.Fail -> verses.map(mapper, BookData.Empty(), 0, true)
        chapters is ChaptersData.Fail -> {
            val errorType = chapters.map(ErrorChaptersDataToDomainMapper(), BookData.Empty())
            VersesDomain.Fail(errorType)
        }
        else -> VersesDomain.Fail(books.map(BooksDataMapper.Error()))
    }
}