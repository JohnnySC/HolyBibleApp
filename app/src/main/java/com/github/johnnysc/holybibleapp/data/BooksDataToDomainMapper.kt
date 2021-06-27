package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.domain.BookDomain

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<Book>): BookDomain
    fun map(e: Exception): BookDomain
}