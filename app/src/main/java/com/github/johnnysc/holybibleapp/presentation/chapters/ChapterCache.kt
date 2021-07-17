package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 17.07.2021
 **/
interface ChapterCache : Save<Int>, Read<Int> {

    class Base(preferencesProvider: PreferencesProvider) : ChapterCache {

        private val sharedPreferences = preferencesProvider.provideSharedPreferences(
            CHAPTER_ID_FILENAME
        )

        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CHAPTER_ID_KEY, data).apply()
        }

        override fun read() = sharedPreferences.getInt(CHAPTER_ID_KEY, 0)

        private companion object {
            const val CHAPTER_ID_FILENAME = "chapterIdFileName"
            const val CHAPTER_ID_KEY = "chapterIdKey"
        }
    }
}