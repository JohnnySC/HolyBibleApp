package com.github.johnnysc.holybibleapp.domain.books

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDataToDomainMapper : BookDataMapper<BookDomain> {
    override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
        BookDomain.Base(id, name, isFavorite)
}