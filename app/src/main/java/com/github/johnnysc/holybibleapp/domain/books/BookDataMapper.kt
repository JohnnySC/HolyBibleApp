package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.data.books.TestamentTemp

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String, testament: String, isFavorite: Boolean): T

    class Id(private val idContainer: Read<Int>) : BookDataMapper<Boolean> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            idContainer.read() == id
    }

    class CompareTestament(private val testamentTemp: TestamentTemp) : BookDataMapper<Boolean> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            testamentTemp.matches(testament)
    }

    class SaveTestament(private val testamentTemp: Save<String>) : BookDataMapper<Unit> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            testamentTemp.save(testament)
    }
}