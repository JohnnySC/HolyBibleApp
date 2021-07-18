package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 17.07.2021
 **/
open class VerseDb : RealmObject(), VerseRealm {

    @PrimaryKey
    var id: Int = -1
    var verseId: Int = -1
    var text: String = ""

    override fun <T> map(mapper: ToVerseMapper<T>) = mapper.map(id, verseId, text)
}

interface VerseRealm {
    fun <T> map(mapper: ToVerseMapper<T>): T
}