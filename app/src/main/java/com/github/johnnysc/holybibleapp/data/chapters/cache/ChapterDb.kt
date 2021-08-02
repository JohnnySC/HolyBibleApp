package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * See [com.github.johnnysc.holybibleapp.data.chapters.ChapterId]
 * @author Asatryan on 11.07.2021
 **/
open class ChapterDb : RealmObject(), ChapterRealm, Matcher<Int> {

    /**
     * BookId * 1000 + chapterId
     */
    @PrimaryKey
    var id: Int = -1

    override fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean) =
        mapper.map(id, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface ChapterRealm {
    fun <T> map(mapper: ToChapterMapper<T>, isFavorite: Boolean): T
}