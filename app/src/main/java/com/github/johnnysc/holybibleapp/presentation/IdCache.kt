package com.github.johnnysc.holybibleapp.presentation

import android.content.Context

/**
 * @author Asatryan on 11.07.2021
 **/
interface IdCache {
    fun read(): Set<Int>
    fun save(id: Int)
    fun start()
    fun finish()

    class Base(context: Context) : IdCache {

        private val sharedPreferences =
            context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)
        private val idSet = mutableSetOf<Int>()

        override fun read(): Set<Int> {
            val set = sharedPreferences.getStringSet(IDS_KEY, emptySet()) ?: emptySet()
            return set.mapTo(HashSet()) { it.toInt() }
        }

        override fun save(id: Int) {
            idSet.add(id)
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

    class Empty : IdCache {
        override fun read() = emptySet<Int>()
        override fun save(id: Int) = Unit
        override fun start() = Unit
        override fun finish() = Unit
    }
}