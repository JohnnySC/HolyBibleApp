package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 11.07.2021
 **/
interface CollapsedIdsCache : Save<Int>, Read<Set<Int>> {
    fun start()
    fun finish()

    abstract class Abstract(preferencesProvider: PreferencesProvider) : CollapsedIdsCache {
        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getFileName())
        }
        private val idSet = mutableSetOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(getKey(), emptySet()) ?: emptySet()
            return set.mapTo(HashSet()) { it.toInt() }
        }

        override fun save(data: Int) {
            idSet.add(data)
        }

        override fun start() {
            idSet.clear()
        }

        override fun finish() {
            val set = idSet.mapTo(HashSet()) { it.toString() }
            sharedPreferences.edit().putStringSet(getKey(), set).apply()
        }

        protected abstract fun getFileName(): String
        protected abstract fun getKey(): String
    }

    class Base(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider) {

        override fun getFileName() = ID_LIST_NAME
        override fun getKey() = IDS_KEY

        private companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }
    }

    class Mock(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider) {

        override fun getFileName() = ID_LIST_NAME
        override fun getKey() = IDS_KEY

        private companion object {
            const val ID_LIST_NAME = "MockCollapsedItemsIdList"
            const val IDS_KEY = "MockCollapsedItemsIdsKey"
        }
    }

    class Empty : CollapsedIdsCache {
        override fun read() = emptySet<Int>()
        override fun save(data: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}