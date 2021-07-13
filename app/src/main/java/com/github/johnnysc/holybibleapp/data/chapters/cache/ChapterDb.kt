package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * See [com.github.johnnysc.holybibleapp.data.chapters.ChapterId]
 * @author Asatryan on 11.07.2021
 **/
open class ChapterDb : RealmObject(), Abstract.Object<ChapterData, ToChapterMapper> {

    /**
     * BookId * 1000 + chapterId
     */
    @PrimaryKey
    var id: Int = -1

    override fun map(mapper: ToChapterMapper) = mapper.map(id)
}