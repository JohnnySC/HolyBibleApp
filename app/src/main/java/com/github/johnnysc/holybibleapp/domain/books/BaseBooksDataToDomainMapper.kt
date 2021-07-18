package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.books.TestamentTemp

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper<BookDomain>) :
    BooksDataToDomainMapper<BooksDomain>() {

    override fun map(data: List<BookData>): BooksDomain {
        val domainList = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        data.forEach { bookData ->
            if (!bookData.matches(temp)) {
                val testamentType = if (temp.isEmpty())
                    TestamentType.OLD
                else
                    TestamentType.NEW
                domainList.add(BookDomain.Testament(testamentType))
                bookData.save(temp)
            }
            domainList.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(domainList)
    }

    override fun map(e: Exception) = BooksDomain.Fail(errorType(e))
}