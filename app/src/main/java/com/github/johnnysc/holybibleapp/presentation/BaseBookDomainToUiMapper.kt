package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.domain.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.TestamentType

/**
 * @author Asatryan on 03.07.2021
 **/
class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    BookDomainToUiMapper {
    override fun map(id: Int, name: String) = when (id) {
        TestamentType.NEW.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.new_testament)
        )
        TestamentType.OLD.getId() -> BookUi.Testament(
            id,
            resourceProvider.getString(R.string.old_testament)
        )
        else -> BookUi.Base(id, name)
    }
}