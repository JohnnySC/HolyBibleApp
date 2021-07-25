package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import io.realm.RealmObject

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookData : Matcher<TestamentTemp>, Save<TestamentTemp>, Abstract.DataObject {

    fun <T> map(mapper: BookDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: BookDataToDbMapper<T>, db: DbWrapper<T>): T

    fun find(id: Int): Boolean
    fun name() : String//todo this is a getter, fix it

    data class Base(
        private val id: Int,
        private val name: String,
        private val testament: String
    ) : BookData {
        override fun <T> map(mapper: BookDataToDomainMapper<T>) = mapper.map(id, name)

        override fun <E : RealmObject> map(mapper: BookDataToDbMapper<E>, db: DbWrapper<E>) =
            mapper.mapToDb(id, name, testament, db)

        override fun find(id: Int) = this.id == id
        override fun name() = name
        override fun matches(arg: TestamentTemp) = arg.matches(testament)
        override fun save(data: TestamentTemp) = data.save(testament)
    }
}