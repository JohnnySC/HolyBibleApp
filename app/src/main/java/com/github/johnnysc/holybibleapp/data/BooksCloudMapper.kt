package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksCloudMapper : Abstract.Mapper {

    fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val bookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>) = cloudList.map { bookCloud ->
            bookCloud.map(bookMapper)
        }
    }
}