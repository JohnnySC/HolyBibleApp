package com.github.johnnysc.holybibleapp.data.net

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book

/**
 * @author Asatryan on 26.06.2021
 **/
interface BookCloudMapper : Abstract.Mapper {

    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String) = Book(id, name)
    }
}