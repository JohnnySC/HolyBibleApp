package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.core.Limits
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDb
import io.realm.RealmObject

/**
 * @author Asatryan on 13.07.2021
 **/
interface ChapterId : Limits {

    fun <T> map(mapper: ChapterIdToUiMapper<T>): T
    fun <T : RealmObject> map(db: DbWrapper<T>): T

    class Base : ChapterId {

        private val bookId: Int //[1 - 66]
        private val chapterIdReal: Int //[1 - 999]
        private val chapterIdGenerated: Int// [1001 - 66999]

        constructor(
            bookId: Int,
            chapterIdReal: Int = 0,
            chapterIdGenerated: Int = 0
        ) {
            this.bookId = bookId
            if (chapterIdReal == 0) {
                this.chapterIdGenerated = chapterIdGenerated
                this.chapterIdReal = chapterIdGenerated % MULTIPLY
            } else {
                this.chapterIdReal = chapterIdReal
                this.chapterIdGenerated = MULTIPLY * bookId + chapterIdReal
            }
        }

        override fun min() = MULTIPLY * bookId
        override fun max() = MULTIPLY * (bookId + 1)
        override fun <T> map(mapper: ChapterIdToUiMapper<T>) = mapper.map(chapterIdReal)

        override fun <T : RealmObject> map(db: DbWrapper<T>) = db.createObject(chapterIdGenerated)

        private companion object {
            const val MULTIPLY = 1000
        }
    }
}