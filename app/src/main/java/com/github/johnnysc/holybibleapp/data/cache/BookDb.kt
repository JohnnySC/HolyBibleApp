package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class BookDb : RealmObject(), Abstract.Mapable<Book, BookCacheMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""

    override fun map(mapper: BookCacheMapper) = Book(id, name)
}