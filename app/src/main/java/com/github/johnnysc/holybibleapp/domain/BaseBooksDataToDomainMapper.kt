package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.TestamentTemp
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) :
    BooksDataToDomainMapper {

    override fun map(books: List<BookData>): BooksDomain {
        val data = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.matches(temp)) {
                if (temp.isEmpty())
                    data.add(BookDomain.Testament(TestamentType.OLD))
                else
                    data.add(BookDomain.Testament(TestamentType.NEW))
                bookData.saveTestament(temp)
            }
            data.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(data)
    }

    override fun map(e: Exception) = BooksDomain.Fail(
        when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}