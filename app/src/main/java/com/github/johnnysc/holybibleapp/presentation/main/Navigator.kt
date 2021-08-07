package com.github.johnnysc.holybibleapp.presentation.main

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.presentation.books.BooksFragment
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersFragment
import com.github.johnnysc.holybibleapp.presentation.core.FeatureNavigator
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesFragment
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesNavigator
import com.github.johnnysc.holybibleapp.presentation.verses.VersesFragment
import com.github.johnnysc.holybibleapp.presentation.verses.VersesNavigator
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 13.07.2021
 **/
interface Navigator : MainNavigator, FeatureNavigator, VersesNavigator, LanguagesNavigator,
    Save<Int>, Read<Int> {

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val currentScreenKey: String,
    ) : Navigator {
        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)

        private val screens = listOf(
            BooksFragment::class.java,
            ChaptersFragment::class.java,
            VersesFragment.Base::class.java,
            LanguagesFragment::class.java
        )

        private var languagesChosen = false

        override fun save(data: Int) {
            languagesChosen = false
            sharedPreferences.edit().putInt(currentScreenKey, data).apply()
        }

        override fun read() = sharedPreferences.getInt(currentScreenKey, LANGUAGE_SCREEN)

        override fun fragment(id: Int): BaseFragment<*> {
            val finalId =
                if (id == LANGUAGE_SCREEN) screens.indexOf(LanguagesFragment::class.java) else id
            return screens[finalId].newInstance()
        }

        override fun showLanguagesFragment(communication: NavigationCommunication) =
            communication.map(LANGUAGE_SCREEN)

        override fun canGoBack() = canNavigateTo(previousScreen())

        override fun navigateBack(navigationCommunication: NavigationCommunication) =
            navigationCommunication.map(previousScreen())

        override fun save(feature: Feature) = when (feature) {
            Feature.BOOKS -> save(BOOKS_SCREEN)
            Feature.CHAPTERS -> save(CHAPTERS_SCREEN)
            Feature.VERSES -> save(VERSES_SCREEN)
            Feature.LANGUAGES -> { languagesChosen = true }
            else -> throw IllegalStateException("nothing for $feature")
        }

        override fun nextFromLanguages(): Int = read().let { saved ->
            return if (saved == LANGUAGE_SCREEN) BOOKS_SCREEN else saved
        }

        override fun nextScreen(navigationCommunication: NavigationCommunication) {
            navigationCommunication.map(read() + 1)
        }

        private fun previousScreen(): Int {
            var previous = Int.MIN_VALUE
            val savedScreen = read()
            if (languagesChosen) {
                if (savedScreen >= 0) previous = savedScreen
            } else {
                if (savedScreen > 0) previous = savedScreen - 1
            }
            return previous
        }

        private fun canNavigateTo(previousScreen: Int) =
            previousScreen >= 0 && previousScreen < screens.size

        private companion object {

            const val LANGUAGE_SCREEN = -1
            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
            const val VERSES_SCREEN = 2
        }
    }

    class Base(preferencesProvider: PreferencesProvider) : Navigator.Abstract(
        preferencesProvider, NAVIGATOR_FILE_NAME, CURRENT_SCREEN_KEY) {
        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
        }
    }

    class Mock(preferencesProvider: PreferencesProvider) : Navigator.Abstract(
        preferencesProvider, NAVIGATOR_FILE_NAME, CURRENT_SCREEN_KEY) {
        private companion object {
            const val NAVIGATOR_FILE_NAME = "mockNavigation"
            const val CURRENT_SCREEN_KEY = "mockScreenId"
        }
    }
}