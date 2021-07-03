package com.github.johnnysc.holybibleapp.data.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class BookDb : RealmObject(), Abstract.Object<BookData, ToBookMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun map(mapper: ToBookMapper) = BookData(id, name, testament)
}