package com.github.johnnysc.holybibleapp.core

import android.content.Context
import com.github.johnnysc.holybibleapp.presentation.languages.ChooseLanguages
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * @author Asatryan on 27.06.2021
 **/
interface RealmProvider : ChooseLanguages {

    fun provide(): Realm

    abstract class Abstract(
        context: Context,
        private val chosenLanguage: ChosenLanguage
    ) : RealmProvider {
        protected abstract fun fileNameEnglish(): String
        protected abstract fun fileNameRussian(): String

        private val config by lazy {
            getConfig(if (chosenLanguage.isChosenRussian()) fileNameRussian() else fileNameEnglish())
        }

        init {
            Realm.init(context)
            Realm.setDefaultConfiguration(config)
        }

        override fun provide(): Realm = Realm.getDefaultInstance()

        override fun chooseEnglish() =
            Realm.setDefaultConfiguration(getConfig(fileNameEnglish()))

        override fun chooseRussian() =
            Realm.setDefaultConfiguration(getConfig(fileNameRussian()))

        private fun getConfig(name: String) = RealmConfiguration.Builder()
            .schemaVersion(SCHEMA_VERSION)
            .name(name)
            .build()

        private companion object {
            const val SCHEMA_VERSION = 0L
        }
    }

    class Base(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {
        override fun fileNameEnglish() = FILE_NAME
        override fun fileNameRussian() = FILE_NAME_RU

        private companion object {
            const val FILE_NAME = "HolyBibleAppRealm"
            const val FILE_NAME_RU = "HolyBibleAppRealmRu"
        }
    }

    class Mock(
        context: Context,
        chosenLanguage: ChosenLanguage
    ) : RealmProvider.Abstract(context, chosenLanguage) {
        override fun fileNameEnglish() = FILE_NAME_MOCK
        override fun fileNameRussian() = FILE_NAME_RU_MOCK

        private companion object {
            const val FILE_NAME_MOCK = "HolyBibleAppRealmMock"
            const val FILE_NAME_RU_MOCK = "HolyBibleAppRealmRuMock"
        }
    }
}