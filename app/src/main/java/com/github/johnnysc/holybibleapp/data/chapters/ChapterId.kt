package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDb
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUi

/**
 * @author Asatryan on 13.07.2021
 **/
interface ChapterId : Abstract.Object<ChapterUi, ChapterIdToUiMapper> {
    fun min(): Int
    fun max(): Int

    fun mapToDb(db: DbWrapper<ChapterDb>): ChapterDb

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

        override fun mapToDb(db: DbWrapper<ChapterDb>) = db.createObject(chapterIdGenerated)
        override fun map(mapper: ChapterIdToUiMapper) =
            mapper.map(chapterIdGenerated, chapterIdReal)

        private companion object {
            const val MULTIPLY = 1000
        }
    }
}