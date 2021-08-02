package com.github.johnnysc.holybibleapp.core

import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 28.07.2021
 **/
interface ScrollPositionCache {

    fun saveScrollPosition(feature: Feature, position: Int)
    fun scrollPosition(feature: Feature): Int

    abstract class Abstract(provider: PreferencesProvider) :
        ScrollPositionCache {

        private val sharedPreferences by lazy { provider.provideSharedPreferences(fileName()) }

        override fun saveScrollPosition(feature: Feature, position: Int) {
            when (feature) {
                Feature.BOOKS -> saveBooksScrollPosition(position)
                Feature.CHAPTERS -> saveChaptersScrollPosition(position)
                Feature.VERSES -> saveVersesScrollPosition(position)
                else -> throw IllegalStateException("unknown feature $feature")
            }
        }

        override fun scrollPosition(feature: Feature) = when (feature) {
            Feature.BOOKS,
            Feature.CHAPTERS,
            Feature.VERSES -> get(feature.name)
            else -> throw IllegalStateException("unknown feature $feature")
        }

        private fun saveBooksScrollPosition(position: Int) {
            save(Feature.BOOKS.name, position)
            save(Feature.CHAPTERS.name, 0)
            save(Feature.VERSES.name, 0)
        }

        private fun saveChaptersScrollPosition(position: Int) {
            save(Feature.CHAPTERS.name, position)
            save(Feature.VERSES.name, 0)
        }

        private fun saveVersesScrollPosition(position: Int) = save(Feature.VERSES.name, position)

        protected abstract fun fileName(): String
        protected abstract fun keySuffix(): String

        private fun save(featureName: String, position: Int) =
            sharedPreferences.edit().putInt(featureName + keySuffix(), position).apply()

        private fun get(featureName: String) =
            sharedPreferences.getInt(featureName + keySuffix(), 0)
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