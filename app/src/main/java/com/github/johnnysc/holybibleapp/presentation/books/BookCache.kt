package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 11.07.2021
 **/
interface BookCache : Save<Pair<Int, String>>, Read<Pair<Int, String>> {

    class Base(preferencesProvider: PreferencesProvider) : BookCache {
        private val sharedPreferences =
           preferencesProvider.provideSharedPreferences(BOOK_ID_FILENAME)

        override fun read() = Pair(
            sharedPreferences.getInt(BOOK_ID_KEY, 0),
            sharedPreferences.getString(BOOK_NAME_KEY, "") ?: ""
        )

        override fun save(data: Pair<Int, String>) =
            sharedPreferences.edit()
                .putInt(BOOK_ID_KEY, data.first)
                .putString(BOOK_NAME_KEY, data.second)
                .apply()

        private companion object {
            const val BOOK_ID_FILENAME = "bookId"
            const val BOOK_ID_KEY = "bookIdKey"
            const val BOOK_NAME_KEY = "bookNameKey"
        }
    }
}