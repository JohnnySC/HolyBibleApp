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
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    resourceProvider: ResourceProvider
) : VersesDomainToUiMapper<VersesUi>(resourceProvider) {

    override fun map(data: Pair<List<VerseDomain>, String>) = VersesUi.Base(
        data.first.map { verse -> verse.map(mapper) }, data.second)

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(listOf(VerseUi.Fail(it)), it)
    }
}