package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.BooksDataToDomainMapper

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDataToDomainMapper(private val bookMapper:BookDataToDomainMapper) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>) = BooksDomain.Success(books, bookMapper)
    override fun map(e: Exception) = BooksDomain.Fail(e)
}