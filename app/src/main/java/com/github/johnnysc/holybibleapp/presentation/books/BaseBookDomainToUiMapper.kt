package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.books.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.books.TestamentType

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper<BookUi> {
    override fun map(id: Int, name: String) = when {
        TestamentType.NEW.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.string(R.string.new_testament)
        )
        TestamentType.OLD.matches(id) -> BookUi.Testament(
            id,
            resourceProvider.string(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }
}