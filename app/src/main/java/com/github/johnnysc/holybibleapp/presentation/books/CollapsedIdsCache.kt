package com.github.johnnysc.holybibleapp.presentation.books

import android.content.Context
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 11.07.2021
 **/
interface CollapsedIdsCache : Save<Int>, Read<Set<Int>> {
    fun start()
    fun finish()

    class Base(context: Context) : CollapsedIdsCache {

        private val sharedPreferences =
            context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)
        private val idSet = mutableSetOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(IDS_KEY, emptySet()) ?: emptySet()
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
            sharedPreferences.edit().putStringSet(IDS_KEY, set).apply()
        }

        private companion object {
            const val ID_LIST_NAME = "collapsedItemsIdList"
            const val IDS_KEY = "collapsedItemsIdsKey"
        }
    }

    class Empty : CollapsedIdsCache {
        override fun read() = emptySet<Int>()
        override fun save(data: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}