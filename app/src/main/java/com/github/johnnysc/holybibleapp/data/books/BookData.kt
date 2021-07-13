package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BookDb
import com.github.johnnysc.holybibleapp.domain.books.BookDomain

/**
 * @author Asatryan on 03.07.2021
 **/
data class BookData(private val id: Int, private val name: String, private val testament: String) :
    Abstract.Object.ToDb<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper>,
    Matcher<TestamentTemp>,
    Save<TestamentTemp> {

    override fun map(mapper: BookDataToDomainMapper) = mapper.map(id, name)
    override fun mapBy(mapper: BookDataToDbMapper, db: DbWrapper<BookDb>) =
        mapper.mapToDb(id, name, testament, db)

    override fun matches(arg: TestamentTemp) = arg.matches(testament)
    override fun save(data: TestamentTemp) = data.save(testament)
}