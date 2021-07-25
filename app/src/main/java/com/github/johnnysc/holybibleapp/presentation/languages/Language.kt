package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read

/**
 * @author Asatryan on 18.07.2021
 **/
interface Language : ChooseLanguages, ChosenLanguage, Read<Int> {

    abstract class Abstract(preferencesProvider: PreferencesProvider) : Language {

        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getLanguageFileName())
        }

        protected abstract fun getLanguageFileName(): String
        protected abstract fun getLanguageKey(): String

        override fun read() = sharedPreferences.getInt(getLanguageKey(), -1)
        override fun chooseEnglish() = save(ENGLISH)
        override fun chooseRussian() = save(RUSSIAN)
        override fun isChosenEnglish() = read() == ENGLISH
        override fun isChosenRussian() = read() == RUSSIAN

        private fun save(language: Int) {
            sharedPreferences.edit().putInt(getLanguageKey(), language).apply()
        }

        private companion object {
            const val ENGLISH = 0
            const val RUSSIAN = 1
        }
    }

    class Base(resourceProvider: PreferencesProvider) : Abstract(resourceProvider) {
        override fun getLanguageFileName() = LANGUAGES_FILE_NAME
        override fun getLanguageKey() = LANGUAGES_KEY

        private companion object {
            const val LANGUAGES_FILE_NAME = "languagesFileName"
            const val LANGUAGES_KEY = "languagesKey"
        }
    }

    class Mock(resourceProvider: PreferencesProvider) : Abstract(resourceProvider) {
        override fun getLanguageFileName() = LANGUAGES_FILE_NAME
        override fun getLanguageKey() = LANGUAGES_KEY

        private companion object {
            const val LANGUAGES_FILE_NAME = "mockLanguagesFileName"
            const val LANGUAGES_KEY = "mockLanguagesKey"
        }
    }

    class Change(
        private val language: Language,
        private val chooseLanguages: ChooseLanguages,
        private val chooseLanguagesResources: ChooseLanguages
    ) : Language {

        override fun chooseEnglish() {
            language.chooseEnglish()
            chooseLanguages.chooseEnglish()
            chooseLanguagesResources.chooseEnglish()
        }

        override fun chooseRussian() {
            language.chooseRussian()
            chooseLanguages.chooseRussian()
            chooseLanguagesResources.chooseRussian()
        }

        override fun isChosenEnglish() = language.isChosenEnglish()
        override fun isChosenRussian() = language.isChosenRussian()
        override fun read() = language.read()
    }
}

interface ChosenLanguage {
    fun isChosenEnglish(): Boolean
    fun isChosenRussian(): Boolean
}

interface ChooseLanguages {
    fun chooseEnglish()
    fun chooseRussian()
}