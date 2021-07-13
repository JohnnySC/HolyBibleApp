package com.github.johnnysc.holybibleapp.core

import io.realm.Realm

/**
 * @author Asatryan on 27.06.2021
 **/
interface RealmProvider {

    fun provide(): Realm

    class Base : RealmProvider {
        override fun provide(): Realm = Realm.getDefaultInstance()
    }
}