package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.core.Limits
import com.github.johnnysc.holybibleapp.core.Multiply
import io.realm.RealmObject

/**
 * @author Asatryan on 13.07.2021
 **/
interface ChapterId : Limits {

    fun <T> map(mapper: ChapterIdToUiMapper<T>, isFavorite: Boolean): T
    fun <T : RealmObject> map(db: DbWrapper<T>): T

    class Base(
        private val bookId: Int, //[1 - 66]
        chapterIdReal: Int = 0,
        chapterIdGenerated: Int = 0,
        private val multiply: Multiply
    ) : ChapterId {
        private val chapterIdReal: Int//[1 - 999]
        private val chapterIdGenerated: Int// [1001 - 66999]

        init {
            if (chapterIdReal == 0) {
                this.chapterIdGenerated = chapterIdGenerated
                this.chapterIdReal = multiply.rest(chapterIdGenerated)
            } else {
                this.chapterIdReal = chapterIdReal
                this.chapterIdGenerated = multiply.map(bookId) + chapterIdReal
            }
        }

        override fun min() = multiply.map(bookId)
        override fun max() = multiply.map(bookId + 1)

        override fun <T> map(mapper: ChapterIdToUiMapper<T>, isFavorite: Boolean) =
            mapper.map(chapterIdReal, chapterIdGenerated, isFavorite)

        override fun <T : RealmObject> map(db: DbWrapper<T>) = db.createObject(chapterIdGenerated)
        //region equals hashCode toString
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Base

            if (bookId != other.bookId) return false
            if (chapterIdReal != other.chapterIdReal) return false
            if (chapterIdGenerated != other.chapterIdGenerated) return false

            return true
        }

        override fun hashCode(): Int {
            var result = bookId
            result = 31 * result + chapterIdReal
            result = 31 * result + chapterIdGenerated
            return result
        }

        override fun toString(): String {
            return "Base(bookId=$bookId, chapterIdReal=$chapterIdReal, chapterIdGenerated=$chapterIdGenerated)"
        }
        //endregion
    }
}