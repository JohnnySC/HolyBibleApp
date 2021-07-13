package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataToDbMapper : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDb>): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDb>): BookDb {
            val bookDb = db.createObject(id)
            bookDb.name = name
            bookDb.testament = testament
            return bookDb
        }
    }
}