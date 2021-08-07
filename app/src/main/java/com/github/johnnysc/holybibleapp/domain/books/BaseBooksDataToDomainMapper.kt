package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.TestamentTemp
import com.github.johnnysc.holybibleapp.domain.core.BaseDataToDomainMapper

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDataToDomainMapper(
    private val bookMapper: BookDataMapper<BookDomain>,
    private val temp: TestamentTemp,
    private val compare: BookDataMapper<Boolean>,
    private val save: BookDataMapper<Unit>,
) : BaseDataToDomainMapper<List<BookData>, BooksDomain>(), BooksDataMapper<BooksDomain> {

    override fun map(data: List<BookData>): BooksDomain {
        val domainList = mutableListOf<BookDomain>()
        data.forEach { bookData ->
            if (!bookData.map(compare)) {
                val testamentType = if (temp.isEmpty())
                    TestamentType.OLD
                else
                    TestamentType.NEW
                domainList.add(BookDomain.Testament(testamentType))
                bookData.map(save)
            }
            domainList.add(bookData.map(bookMapper))
        }
        temp.clear()
        return BooksDomain.Success(domainList)
    }

    override fun map(e: Exception) = BooksDomain.Fail(errorType(e))
}