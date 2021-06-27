package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.presentation.BooksUi

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<Book>) : BooksUi
    fun map(errorType: ErrorType) : BooksUi
}