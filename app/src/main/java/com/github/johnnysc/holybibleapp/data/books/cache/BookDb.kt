package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class BookDb : RealmObject(), BookRealm {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun <T> map(mapper: ToBookMapper<T>) = mapper.map(id, name, testament)
}

interface BookRealm {
    fun <T> map(mapper: ToBookMapper<T>): T
}