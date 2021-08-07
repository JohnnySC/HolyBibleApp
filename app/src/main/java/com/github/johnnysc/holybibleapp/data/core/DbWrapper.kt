package com.github.johnnysc.holybibleapp.data.core

import io.realm.Realm
import io.realm.RealmObject

/**
 * @author Asatryan on 03.07.2021
 **/
interface DbWrapper<T : RealmObject> {

    fun createObject(id: Int): T

    abstract class Base<T : RealmObject>(private val realm: Realm) : DbWrapper<T> {
        override fun createObject(id: Int): T = realm.createObject(dbClass(), id)
        protected abstract fun dbClass(): Class<T>
    }
}