package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.core.ChooseLanguages
import com.github.johnnysc.holybibleapp.core.ChosenLanguage
import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read

/**
 * @author Asatryan on 18.07.2021
 **/
interface Language : ChooseLanguages, ChosenLanguage, Read<Int> {

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val key: String
    ) : Language {

        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)

        override fun read() = sharedPreferences.getInt(key, -1)
        override fun chooseEnglish() = save(ENGLISH)
        override fun chooseRussian() = save(RUSSIAN)
        override fun isChosenEnglish() = read() == ENGLISH
        override fun isChosenRussian() = read() == RUSSIAN

        private fun save(language: Int) {
            sharedPreferences.edit().putInt(key, language).apply()
        }

        private companion object {
            const val ENGLISH = 0
            const val RUSSIAN = 1
        }
    }

    class Base(resourceProvider: PreferencesProvider) : Abstract(resourceProvider, FILE_NAME, KEY) {
        private companion object {
            const val FILE_NAME = "languagesFileName"
            const val KEY = "languagesKey"
        }
    }

    class Mock(resourceProvider: PreferencesProvider) : Abstract(resourceProvider, FILE_NAME, KEY) {
        private companion object {
            const val FILE_NAME = "mockLanguagesFileName"
            const val KEY = "mockLanguagesKey"
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