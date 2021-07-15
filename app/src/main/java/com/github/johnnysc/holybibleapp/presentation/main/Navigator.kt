package com.github.johnnysc.holybibleapp.presentation.main

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.presentation.books.BooksFragment
import com.github.johnnysc.holybibleapp.presentation.books.BooksNavigator
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersFragment
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersNavigator

/**
 * @author Asatryan on 13.07.2021
 **/
interface Navigator : Save<Int>, Read<Int>, MainNavigator, BooksNavigator, ChaptersNavigator {

    class Base(preferencesProvider: PreferencesProvider) : Navigator {

        private val sharedPreferences =
            preferencesProvider.provideSharedPreferences(NAVIGATOR_FILE_NAME)

        private val screens = listOf(
            BooksFragment::class.java,
            ChaptersFragment::class.java
        )

        override fun save(data: Int) =
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()

        override fun read() = sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)

        override fun getFragment(id: Int): BaseFragment = screens[id].newInstance()

        override fun saveBooksScreen() = save(BOOKS_SCREEN)
        override fun saveChaptersScreen() = save(CHAPTERS_SCREEN)

        override fun nextScreen(navigationCommunication: NavigationCommunication) =
            navigationCommunication.map(read() + 1)

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"

            const val BOOKS_SCREEN = 0
            const val CHAPTERS_SCREEN = 1
        }
    }
}