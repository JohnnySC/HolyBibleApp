package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.CommonBookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheMapper : Abstract.Mapper.Data<List<CommonBookData>, List<BookData>> {

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(data: List<CommonBookData>) = data.map { bookDb ->
            bookDb.map(mapper)
        }
    }
}