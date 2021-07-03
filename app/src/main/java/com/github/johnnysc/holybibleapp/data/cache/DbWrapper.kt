package com.github.johnnysc.holybibleapp.data.cache

import io.realm.Realm

/**
 * @author Asatryan on 03.07.2021
 **/
interface DbWrapper {

    fun createObject(id: Int): BookDb

    class Base(private val realm: Realm) : DbWrapper {
        override fun createObject(id: Int): BookDb {
            return realm.createObject(BookDb::class.java, id)
        }
    }
}