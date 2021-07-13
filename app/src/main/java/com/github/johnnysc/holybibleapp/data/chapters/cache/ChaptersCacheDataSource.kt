package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.core.RealmProvider
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import io.realm.Realm

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCacheDataSource : Save<List<ChapterData>> {

    fun fetchChapters(bookId: Int): List<ChapterDb>

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ChapterDataToDbMapper
    ) : ChaptersCacheDataSource {

        override fun fetchChapters(bookId: Int): List<ChapterDb> {
            val chapterId = ChapterId.Base(bookId)
            realmProvider.provide().use { realm ->
                val chapters = realm.where(ChapterDb::class.java)
                    .between("id", chapterId.min(), chapterId.max())
                    .findAll()
                return realm.copyFromRealm(chapters)
            }
        }

        override fun save(data: List<ChapterData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    data.forEach { chapter ->
                        chapter.mapBy(mapper, ChapterDbWrapper(realm))
                    }
                }
            }
        }

        private inner class ChapterDbWrapper(realm: Realm) : DbWrapper.Base<ChapterDb>(realm) {
            override fun dbClass() = ChapterDb::class.java
        }
    }
}