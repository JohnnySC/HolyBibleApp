package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<BookDb>): List<Book>

    class Base(private val mapper: BookCacheMapper) : BooksCacheMapper {
        override fun map(books: List<BookDb>) = books.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}