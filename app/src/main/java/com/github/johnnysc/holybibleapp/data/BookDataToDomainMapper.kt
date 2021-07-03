package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.BookDomain

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String) : BookDomain
}