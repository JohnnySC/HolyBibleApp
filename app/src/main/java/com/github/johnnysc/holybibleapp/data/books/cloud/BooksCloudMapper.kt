package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.isFavorite
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCloudMapper :
    Abstract.Mapper.Data<Pair<List<BookCloud>, List<Int>>, List<BookData>> {

    class Base(private val bookMapper: ToBookMapper<BookData>) : BooksCloudMapper {
        override fun map(data: Pair<List<BookCloud>, List<Int>>) = data.first.map { bookCloud ->
            bookCloud.map(bookMapper, data.second.isFavorite(bookCloud))
        }
    }
}