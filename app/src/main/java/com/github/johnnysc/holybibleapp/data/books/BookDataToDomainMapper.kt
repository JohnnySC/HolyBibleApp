package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataToDomainMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String, isFavorite: Boolean): T
}