package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.data.BooksDataToDomainMapper

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>) = BooksDomain.Success(books)
    override fun map(e: Exception) = BooksDomain.Fail(e)
}