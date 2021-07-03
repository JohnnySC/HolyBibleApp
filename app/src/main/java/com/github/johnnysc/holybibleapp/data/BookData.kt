package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.cache.BookDb
import com.github.johnnysc.holybibleapp.domain.BookDomain
import io.realm.Realm

/**
 * @author Asatryan on 03.07.2021
 **/
class BookData(private val id: Int, private val name: String) :
    ToBookDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper, realm: Realm) = mapper.mapToDb(id, name, realm)
}

//todo make it better later
interface ToBookDb<T, M : Abstract.Mapper> {

    fun mapTo(mapper: M, realm: Realm): T
}