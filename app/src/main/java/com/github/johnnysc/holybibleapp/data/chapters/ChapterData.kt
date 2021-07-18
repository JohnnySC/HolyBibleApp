package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import io.realm.RealmObject

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterData : Abstract.DataObject {
    fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>): T
    fun <T> map(mapper: ChapterDataToDomainMapper<T>): T

    data class Base(private val chapterId: ChapterId) : ChapterData {
        override fun <T : RealmObject> map(mapper: ChapterDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapTo(chapterId, db)

        override fun <T> map(mapper: ChapterDataToDomainMapper<T>) = mapper.map(chapterId)
    }
}