package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.TestamentTemp
import com.github.johnnysc.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Asatryan on 27.06.2021
 **/
sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    class Success(
        private val books: List<BookData>,
        private val bookMapper: BookDataToDomainMapper
    ) : BooksDomain() {

        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
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
            return mapper.map(data)
        }
    }

    class Fail(private val e: Exception) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}