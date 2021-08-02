package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class BookDb : RealmObject(), BookRealm, Matcher<Int> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean) =
        mapper.map(id, name, testament, isFavorite)

    override fun matches(arg: Int) = arg == id
}

interface BookRealm {
    fun <T> map(mapper: ToBookMapper<T>, isFavorite: Boolean): T
}