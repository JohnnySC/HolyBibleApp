package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.cache.BookDb
import com.github.johnnysc.holybibleapp.data.cache.DbWrapper
import com.github.johnnysc.holybibleapp.domain.BookDomain

/**
 * @author Asatryan on 03.07.2021
 **/
data class BookData(private val id: Int, private val name: String, private val testament: String) :
    ToBookDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper> {
    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapTo(mapper: BookDataToDbMapper, db: DbWrapper) =
        mapper.mapToDb(id, name, testament, db)

    fun matches(temp: TestamentTemp) = temp.matches(testament)
    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

//todo make it better later
interface ToBookDb<T, M : Abstract.Mapper> {

    fun mapTo(mapper: M, db: DbWrapper): T
}