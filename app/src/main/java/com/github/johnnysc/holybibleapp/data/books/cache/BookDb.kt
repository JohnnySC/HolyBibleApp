package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.data.books.CommonBookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 27.06.2021
 **/
open class BookDb : RealmObject(), CommonBookData {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun map(mapper: ToBookMapper) = mapper.map(id, name, testament)
}