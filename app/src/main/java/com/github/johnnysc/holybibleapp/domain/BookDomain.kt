package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.BookUi

/**
 * @author Asatryan on 03.07.2021
 **/
sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {

    data class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(type.getId(), type.name)
    }
}