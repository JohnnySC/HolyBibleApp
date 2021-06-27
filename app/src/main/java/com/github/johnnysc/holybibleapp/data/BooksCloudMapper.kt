package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.data.net.BookCloud
import com.github.johnnysc.holybibleapp.data.net.BookCloudMapper

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<BookCloud>): List<Book>

    class Base(private val bookMapper: BookCloudMapper) : BooksCloudMapper {
        override fun map(cloudList: List<BookCloud>) = cloudList.map { bookCloud ->
            bookCloud.map(bookMapper)
        }
    }
}