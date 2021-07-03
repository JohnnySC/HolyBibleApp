package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.ToBookMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheMapper : Abstract.Mapper {

    fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookMapper>>) = books.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}