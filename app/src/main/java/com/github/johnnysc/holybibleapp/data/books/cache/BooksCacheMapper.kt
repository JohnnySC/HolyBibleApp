package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.isFavorite
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheMapper : Abstract.Mapper.Data<Pair<List<BookDb>, List<Int>>, List<BookData>> {

    class Base(private val mapper: ToBookMapper<BookData>) : BooksCacheMapper {
        override fun map(data: Pair<List<BookDb>, List<Int>>) = data.first.map { bookDb ->
            bookDb.map(mapper, data.second.isFavorite(bookDb))
        }
    }
}