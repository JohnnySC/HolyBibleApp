package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * See [com.github.johnnysc.holybibleapp.data.chapters.ChapterId]
 * @author Asatryan on 11.07.2021
 **/
open class ChapterDb : RealmObject(), ChapterRealm {

    /**
     * BookId * 1000 + chapterId
     */
    @PrimaryKey
    var id: Int = -1
    
    override fun <T> map(mapper: ToChapterMapper<T>) = mapper.map(id)
}

interface ChapterRealm {
    fun <T> map(mapper: ToChapterMapper<T>): T
}