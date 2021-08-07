package com.github.johnnysc.holybibleapp.data.core

import android.content.Context
import com.github.johnnysc.holybibleapp.core.ChooseLanguages
import com.github.johnnysc.holybibleapp.core.ChosenLanguage
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * @author Asatryan on 27.06.2021
 **/
interface RealmProvider : ChooseLanguages {

    fun provide(useCommon: Boolean = false): Realm

    abstract class Abstract(
        context: Context,
        chosenLanguage: ChosenLanguage,
        private val fileNameEnglish: String,
        private val fileNameRussian: String,
        private val fileNameCommon: String,
    ) : RealmProvider {

        init {
            Realm.init(context)
            val config = getConfig(if (chosenLanguage.isChosenRussian())
                fileNameRussian
            else
                fileNameEnglish
            )
            Realm.setDefaultConfiguration(config)
        }

        override fun provide(useCommon: Boolean): Realm = if (useCommon)
            Realm.getInstance(getConfig(fileNameCommon))
        else
            Realm.getDefaultInstance()

        override fun chooseEnglish() = Realm.setDefaultConfiguration(getConfig(fileNameEnglish))

        override fun chooseRussian() = Realm.setDefaultConfiguration(getConfig(fileNameRussian))

        private fun getConfig(name: String) = RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .migration(FavoriteMigration())
            .name(name)
            .build()

        private companion object {
            const val SCHEMA_VERSION = 1L
        }
    }

    class Base(context: Context, chosenLanguage: ChosenLanguage) : Abstract(
        context, chosenLanguage, FILE_NAME, FILE_NAME_RU, FILE_NAME_COMMON) {
        private companion object {
            const val FILE_NAME = "HolyBibleAppRealm"
            const val FILE_NAME_RU = "HolyBibleAppRealmRu"
            const val FILE_NAME_COMMON = "HolyBibleAppCommon"
        }
    }

    class Mock(context: Context, chosenLanguage: ChosenLanguage) : Abstract(
        context, chosenLanguage, FILE_NAME_MOCK, FILE_NAME_RU_MOCK, FILE_NAME_COMMON) {
        private companion object {
            const val FILE_NAME_MOCK = "HolyBibleAppRealmMock"
            const val FILE_NAME_RU_MOCK = "HolyBibleAppRealmRuMock"
            const val FILE_NAME_COMMON = "HolyBibleAppCommonMock"
        }
    }
}