package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.data.core.IdCache
import com.github.johnnysc.holybibleapp.core.PreferencesProvider

/**
 * @author Asatryan on 11.07.2021
 **/
interface BookCache : IdCache {

    class Base(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), BookCache {
        private companion object {
            const val FILENAME = "bookId"
            const val KEY = "bookIdKey"
        }
    }

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), BookCache {
        private companion object {
            const val FILENAME = "deeplinkIds"
            const val KEY = "deeplinkBookIdKey"
        }
    }
}