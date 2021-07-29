package com.github.johnnysc.holybibleapp.core

import com.github.johnnysc.holybibleapp.data.books.BooksScrollPositionCache
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersScrollPositionCache
import com.github.johnnysc.holybibleapp.data.verses.VersesScrollPositionCache

/**
 * @author Asatryan on 28.07.2021
 **/
interface ScrollPositionCache : BooksScrollPositionCache, ChaptersScrollPositionCache,
    VersesScrollPositionCache {

    abstract class Abstract(provider: PreferencesProvider) :
        ScrollPositionCache {

        private val sharedPreferences by lazy { provider.provideSharedPreferences(fileName()) }

        override fun saveBooksScrollPosition(position: Int) {
            save(BOOKS, position)
            save(CHAPTERS, 0)
            save(VERSES, 0)
        }

        override fun saveChaptersScrollPosition(position: Int) {
            save(CHAPTERS, position)
            save(VERSES, 0)
        }

        override fun saveVersesScrollPosition(position: Int) = save(VERSES, position)

        override fun booksScrollPosition() = get(BOOKS)
        override fun chaptersScrollPosition() = get(CHAPTERS)
        override fun versesScrollPosition() = get(VERSES)

        protected abstract fun fileName(): String
        protected abstract fun keySuffix(): String

        private fun save(featureName: String, position: Int) =
            sharedPreferences.edit().putInt(featureName + keySuffix(), position).apply()

        private fun get(featureName: String) =
            sharedPreferences.getInt(featureName + keySuffix(), 0)

        private companion object {
            const val BOOKS = "BOOKS"
            const val CHAPTERS = "CHAPTERS"
            const val VERSES = "VERSES"
        }
    }

    class Base(provider: PreferencesProvider) : Abstract(provider) {
        override fun fileName() = SCROLL_POSITION_FILENAME
        override fun keySuffix() = SCROLL_POSITION_KEY_SUFFIX

        private companion object {
            const val SCROLL_POSITION_FILENAME = "scrollPosition"
            const val SCROLL_POSITION_KEY_SUFFIX = "_scroll_position"
        }
    }

    class Mock(provider: PreferencesProvider) : Abstract(provider) {
        override fun fileName() = SCROLL_POSITION_FILENAME
        override fun keySuffix() = SCROLL_POSITION_KEY_SUFFIX

        private companion object {
            const val SCROLL_POSITION_FILENAME = "MockScrollPosition"
            const val SCROLL_POSITION_KEY_SUFFIX = "_mock_scroll_position"
        }
    }
}