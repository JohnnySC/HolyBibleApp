package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.books.BookUi

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDomainToUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String): T
}