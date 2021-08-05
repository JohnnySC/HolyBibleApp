package com.github.johnnysc.holybibleapp.data.verse

import com.github.johnnysc.holybibleapp.core.IdCache
import com.github.johnnysc.holybibleapp.core.PreferencesProvider

/**
 * @author Asatryan on 04.08.2021
 **/
interface VerseIdCache : IdCache {

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILE_NAME, KEY), VerseIdCache {
        private companion object {
            const val FILE_NAME = "deeplinkIds"
            const val KEY = "verseIdKey"
        }
    }
}