package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.core.FavoritesList

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCloudMapper :
    Abstract.Mapper.Data<Pair<List<BookCloud>, FavoritesList>, List<BookData>> {

    class Base(private val bookMapper: ToBookMapper<BookData>) : BooksCloudMapper {
        override fun map(data: Pair<List<BookCloud>, FavoritesList>) = data.let { (books, ids) ->
            books.map { bookCloud -> bookCloud.map(bookMapper, ids.isFavorite(bookCloud)) }
        }
    }
}