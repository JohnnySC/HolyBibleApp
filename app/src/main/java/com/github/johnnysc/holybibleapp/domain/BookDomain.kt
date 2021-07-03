package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.BookUi

/**
 * @author Asatryan on 03.07.2021
 **/
class BookDomain(private val id: Int, private val name: String) : Abstract.Object<BookUi, BookDomainToUiMapper> {
    override fun map(mapper: BookDomainToUiMapper) =  mapper.map(id, name)
}