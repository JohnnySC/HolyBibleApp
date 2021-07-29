package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BuildString
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
    private val resourceProvider: ResourceProvider
) : VersesDomainToUiMapper<VersesUi>(resourceProvider) {

    override fun map(data: Triple<List<VerseDomain>, BuildString, Int>) =
        VersesUi.Base(
            data.first.map { verse -> verse.map(mapper) },
            data.second.build(resourceProvider, R.string.book_and_chapter, data.third)
        )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(listOf(VerseUi.Fail(it)), it)
    }
}