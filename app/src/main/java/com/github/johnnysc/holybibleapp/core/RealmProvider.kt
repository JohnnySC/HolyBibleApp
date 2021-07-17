package com.github.johnnysc.holybibleapp.core

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * @author Asatryan on 27.06.2021
 **/
interface RealmProvider {

    fun provide(): Realm

    class Base(context: Context) : RealmProvider {
        init {
            Realm.init(context)
            val config = RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .name(FILE_NAME)
                .build()
            Realm.setDefaultConfiguration(config)
        }

        override fun provide(): Realm = Realm.getDefaultInstance()

        private companion object {
            const val SCHEMA_VERSION = 0L
            const val FILE_NAME = "HolyBibleAppRealm"
        }
    }
}