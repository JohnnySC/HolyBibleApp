package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.core.FavoritesList

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCacheMapper : Abstract.Mapper.Data<Pair<List<BookDb>, FavoritesList>, List<BookData>> {

    class Base(private val mapper: ToBookMapper<BookData>) : BooksCacheMapper {
        override fun map(data: Pair<List<BookDb>, FavoritesList>) = data.let { (books, ids) ->
            books.map { bookDb -> bookDb.map(mapper, ids.isFavorite(bookDb)) }
        }
    }
}