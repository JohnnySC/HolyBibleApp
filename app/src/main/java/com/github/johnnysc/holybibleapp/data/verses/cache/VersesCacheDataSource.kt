package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import io.realm.Realm

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCacheDataSource : CacheDataSource<VerseData> {

    fun fetchVerses(limits: Limits): List<VerseDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: VerseDataToDbMapper<VerseDb>
    ) : VersesCacheDataSource {
        override fun fetchVerses(limits: Limits): List<VerseDb> {
            realmProvider.provide().use { realm ->
                val verses = realm.where(VerseDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return realm.copyFromRealm(verses)
            }
        }

        override fun save(data: List<VerseData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { verse ->
                        verse.map(mapper, VerseDbWrapper(realm))
                    }
                }
            }
        }

        private inner class VerseDbWrapper(realm: Realm) : DbWrapper.Base<VerseDb>(realm) {
            override fun dbClass() = VerseDb::class.java
        }
    }
}