package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 17.07.2021
 **/
open class VerseDb : RealmObject(), Abstract.Object<VerseData, ToVerseMapper> {

    @PrimaryKey
    var id: Int = -1
    var verseId: Int = -1
    var text: String = ""

    override fun map(mapper: ToVerseMapper) = VerseData(id, verseId, text)
}