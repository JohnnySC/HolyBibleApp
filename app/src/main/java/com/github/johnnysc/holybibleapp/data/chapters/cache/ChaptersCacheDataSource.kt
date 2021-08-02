package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.CacheDataSource
import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.core.Limits
import com.github.johnnysc.holybibleapp.core.RealmProvider
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import io.realm.Realm

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCacheDataSource : CacheDataSource<ChapterData> {

    fun fetchChapters(limits: Limits): List<ChapterDb>

    class Base(
        realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper<ChapterDb>
    ) : CacheDataSource.Abstract<ChapterData>(realmProvider), ChaptersCacheDataSource {

        override fun fetchChapters(limits: Limits): List<ChapterDb> {
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", limits.min(), limits.max())
                    .findAll()
                return realm.copyFromRealm(chapters)
            }
        }

        override fun save(data: List<ChapterData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.map(mapper, ChapterDbWrapper(realm))
                    }
                }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}