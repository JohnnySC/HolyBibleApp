package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.BooksDomain

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain
}