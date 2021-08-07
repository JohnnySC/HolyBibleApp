package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import io.realm.RealmObject

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataToDbMapper<E : RealmObject> : Abstract.Mapper {

    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<E>): E

    class Base : BookDataToDbMapper<BookDb> {
        override fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper<BookDb>) =
            db.createObject(id).apply {
                this.name = name
                this.testament = testament
            }
    }
}