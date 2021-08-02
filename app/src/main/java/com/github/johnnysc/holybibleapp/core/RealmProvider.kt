package com.github.johnnysc.holybibleapp.core

import android.content.Context
import com.github.johnnysc.holybibleapp.presentation.languages.ChooseLanguages
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import io.realm.*
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
interface RealmProvider : ChooseLanguages {

    fun provide(useCommon: Boolean = false): Realm

    abstract class Abstract(
        context: Context,
        private val chosenLanguage: ChosenLanguage
    ) : RealmProvider {
        protected abstract fun fileNameEnglish(): String
        protected abstract fun fileNameRussian(): String
        protected abstract fun fileNameCommon(): String

        private val config by lazy {
            getConfig(if (chosenLanguage.isChosenRussian()) fileNameRussian() else fileNameEnglish())
        }

        init {
            Realm.init(context)
            Realm.setDefaultConfiguration(config)
        }

        override fun provide(useCommon: Boolean): Realm =
            if (useCommon)
                Realm.getInstance(getConfig(fileNameCommon()))
            else
                Realm.getDefaultInstance()

        override fun chooseEnglish() =
            Realm.setDefaultConfiguration(getConfig(fileNameEnglish()))

        override fun chooseRussian() =
            Realm.setDefaultConfiguration(getConfig(fileNameRussian()))

        private fun getConfig(name: String) = RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .migration(FavoriteMigration())
            .name(name)
            .build()

        private companion object {
            const val SCHEMA_VERSION = 1L
        }
    }

    class Base(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {
        override fun fileNameEnglish() = FILE_NAME
        override fun fileNameRussian() = FILE_NAME_RU
        override fun fileNameCommon() = FILE_NAME_COMMON

        private companion object {
            const val FILE_NAME = "HolyBibleAppRealm"
            const val FILE_NAME_RU = "HolyBibleAppRealmRu"
            const val FILE_NAME_COMMON = "HolyBibleAppCommon"
        }
    }

    class Mock(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {
        override fun fileNameEnglish() = FILE_NAME_MOCK
        override fun fileNameRussian() = FILE_NAME_RU_MOCK
        override fun fileNameCommon() = FILE_NAME_COMMON

        private companion object {
            const val FILE_NAME_MOCK = "HolyBibleAppRealmMock"
            const val FILE_NAME_RU_MOCK = "HolyBibleAppRealmRuMock"
            const val FILE_NAME_COMMON = "HolyBibleAppCommonMock"
        }
    }
}

class FavoriteMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        if (oldVersion == 0L)
            schema.create("FavoriteDb")
                .addField("id", Int::class.java, FieldAttribute.PRIMARY_KEY)

    }
}

open class FavoriteDb : RealmObject() {
    @PrimaryKey
    var id: Int = -1
}