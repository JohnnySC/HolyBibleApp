package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.data.books.BookDataToDomainMapper

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDataToDomainMapper : BookDataToDomainMapper<BookDomain> {
    override fun map(id: Int, name: String, isFavorite: Boolean) =
        BookDomain.Base(id, name, isFavorite)
}