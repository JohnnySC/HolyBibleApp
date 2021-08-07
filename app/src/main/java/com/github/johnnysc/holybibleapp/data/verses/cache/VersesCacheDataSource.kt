package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.data.core.CacheDataSource
import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.core.Limits
import com.github.johnnysc.holybibleapp.data.core.RealmProvider
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import io.realm.Realm

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCacheDataSource : CacheDataSource<VerseData> {

    fun fetchVerses(limits: Limits): List<VerseDb>

    class Base(realmProvider: RealmProvider, private val mapper: VerseDataToDbMapper<VerseDb>) :
        CacheDataSource.Abstract<VerseData>(realmProvider), VersesCacheDataSource {
        override fun fetchVerses(limits: Limits): List<VerseDb> =
            realmProvider.provide().use { realm ->
                val verses = realm.where(VerseDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return realm.copyFromRealm(verses)
            }

        override fun save(data: List<VerseData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction { val dbWrapper = VerseDbWrapper(realm)
                data.forEach { verse -> verse.map(mapper, dbWrapper) }
            }
        }

        private inner class VerseDbWrapper(realm: Realm) : DbWrapper.Base<VerseDb>(realm) {
            override fun dbClass() = VerseDb::class.java
        }
    }
}