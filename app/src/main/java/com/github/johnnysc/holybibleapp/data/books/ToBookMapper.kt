package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 26.06.2021
 **/
interface ToBookMapper<T> : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String, isFavorite: Boolean = false): T

    class Base : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            BookData.Base(id, name, testament, isFavorite)
    }
}