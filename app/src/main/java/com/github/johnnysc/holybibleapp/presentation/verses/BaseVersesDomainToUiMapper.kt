package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper,
    resourceProvider: ResourceProvider
) : VersesDomainToUiMapper(resourceProvider) {

    override fun map(data: List<VerseDomain>) = VersesUi(data.map { verse ->
        verse.map(mapper)
    })

    override fun map(errorType: ErrorType) = VersesUi(listOf(VerseUi.Fail(errorMessage(errorType))))
}