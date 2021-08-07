package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.domain.books.BookDataMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.github.johnnysc.holybibleapp.data.verses.VerseDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.core.BaseDataToDomainMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>,
    private val bookMapper: BookDataMapper<BookDomain>,
) : BaseDataToDomainMapper<Triple<List<VerseData>, BookData, Pair<Int, Boolean>>, VersesDomain>(),
    VersesDataToDomainMapper<VersesDomain> {

    override fun map(data: Triple<List<VerseData>, BookData, Pair<Int, Boolean>>): VersesDomain {
        val list = ArrayList(data.first.map { verseData -> verseData.map(mapper) })
        if (!data.third.second)
            list.add(VerseDomain.Next)
        return VersesDomain.Success(list, data.second.map(bookMapper), data.third.first)
    }

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}