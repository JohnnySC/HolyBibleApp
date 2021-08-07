package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.domain.core.BaseDataToDomainMapper
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.data.books.BookData

/**
 * @author Asatryan on 11.07.2021
 **/
interface BooksDataMapper<T> : Abstract.Mapper.DataToDomain<List<BookData>, T> {

    class Id(private val id: Read<Int>) : BooksDataMapper<BookData> {
        override fun map(data: List<BookData>) =
            data.find { it.map(BookDataMapper.Id(id)) } ?: BookData.Empty()

        override fun map(e: Exception) = BookData.Empty()
    }

    class Error : BaseDataToDomainMapper<List<BookData>, ErrorType>(), BooksDataMapper<ErrorType> {
        override fun map(data: List<BookData>) = ErrorType.GENERIC_ERROR
        override fun map(e: Exception) = errorType(e)
    }
}