package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDataToDbMapper
import io.realm.RealmObject

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseData : Abstract.DataObject {

    fun <T> map(mapper: VerseDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: VerseDataToDbMapper<T>, db: DbWrapper<T>): T

    data class Base(
        private val id: Int,
        private val verseId: Int,
        private val text: String,
        private val isFavorite: Boolean = false,
    ) : VerseData {
        override fun <T> map(mapper: VerseDataToDomainMapper<T>) =
            mapper.map(id, verseId, text, isFavorite)

        override fun <T : RealmObject> map(mapper: VerseDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.map(id, verseId, text, db)
    }
}