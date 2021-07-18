package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import io.realm.RealmObject

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDataToDbMapper<T : RealmObject> {

    fun mapTo(chapterId: ChapterId, db: DbWrapper<T>): T

    class Base : ChapterDataToDbMapper<ChapterDb> {
        override fun mapTo(chapterId: ChapterId, db: DbWrapper<ChapterDb>) = chapterId.map(db)
    }
}