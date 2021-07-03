package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.domain.BookDomainToUiMapper

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDomainToUiMapper : BookDomainToUiMapper {
    override fun map(id: Int, name: String) = BookUi.Base(id, name)
}