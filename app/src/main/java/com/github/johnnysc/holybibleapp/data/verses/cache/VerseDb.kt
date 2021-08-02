package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 17.07.2021
 **/
open class VerseDb : RealmObject(), VerseRealm, Matcher<Int> {

    @PrimaryKey
    var id: Int = -1
    var verseId: Int = -1
    var text: String = ""

    override fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean) =
        mapper.map(id, verseId, text, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface VerseRealm {
    fun <T> map(mapper: ToVerseMapper<T>, isFavorite: Boolean): T
}