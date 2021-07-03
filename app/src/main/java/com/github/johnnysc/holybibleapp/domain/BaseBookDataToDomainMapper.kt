package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDataToDomainMapper : BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain(id, name)
}