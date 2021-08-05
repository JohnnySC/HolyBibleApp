package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.IdCache
import com.github.johnnysc.holybibleapp.core.PreferencesProvider

/**
 * @author Asatryan on 17.07.2021
 **/
interface ChapterCache : IdCache {

    class Base(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), ChapterCache {
        private companion object {
            const val FILENAME = "chapterIdFileName"
            const val KEY = "chapterIdKey"
        }
    }

    class Deeplink(preferencesProvider: PreferencesProvider) :
        IdCache.Abstract(preferencesProvider, FILENAME, KEY), ChapterCache {
        private companion object {
            const val FILENAME = "deeplinkIds"
            const val KEY = "deeplinkChapterIdKey"
        }
    }
}