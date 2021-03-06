package com.github.johnnysc.holybibleapp.data.core

import com.github.johnnysc.holybibleapp.core.PreferencesProvider
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 05.08.2021
 **/
interface IdCache : Save<Int>, Read<Int> {

    abstract class Abstract(
        preferencesProvider: PreferencesProvider,
        fileName: String,
        private val key: String
    ) : IdCache {
        private val sharedPreferences = preferencesProvider.provideSharedPreferences(fileName)
        override fun read() = sharedPreferences.getInt(key, 0)
        override fun save(data: Int) {
            sharedPreferences.edit().putInt(key, data).apply()
        }
    }
}