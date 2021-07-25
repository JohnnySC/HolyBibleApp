package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 11.07.2021
 **/
interface BookCache : Save<Int>, Read<Int> {

    class Base(preferencesProvider: PreferencesProvider) : BookCache {
        private val sharedPreferences =
            preferencesProvider.provideSharedPreferences(BOOK_ID_FILENAME)

        override fun read() = sharedPreferences.getInt(BOOK_ID_KEY, 0)
        override fun save(data: Int) = sharedPreferences.edit().putInt(BOOK_ID_KEY, data).apply()

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
        }
    }
}