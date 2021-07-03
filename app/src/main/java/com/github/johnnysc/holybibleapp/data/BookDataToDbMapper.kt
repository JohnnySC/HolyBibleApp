package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.cache.BookDb
import io.realm.Realm

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, realm: Realm): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, realm: Realm): BookDb {
            val bookDb = realm.createObject(BookDb::class.java, id)
            bookDb.name = name
            return bookDb
        }
    }
}