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

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val key: String
    ) : CollapsedIdsCache {
        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)
        private val idSet = mutableSetOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(key, emptySet()) ?: emptySet()
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
            sharedPreferences.edit().putStringSet(key, set).apply()
        }
    }

    class Base(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider, ID_LIST_NAME, IDS_KEY) {
        private companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }
    }

    class Mock(preferencesProvider: PreferencesProvider) :
        CollapsedIdsCache.Abstract(preferencesProvider, ID_LIST_NAME, IDS_KEY) {
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