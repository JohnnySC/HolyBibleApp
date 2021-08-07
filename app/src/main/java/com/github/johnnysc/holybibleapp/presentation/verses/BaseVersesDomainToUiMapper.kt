package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.books.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.core.BaseDomainToUiMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesDomainToUiMapper(
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    private val resourceProvider: ResourceProvider,
) : BaseDomainToUiMapper<Triple<List<VerseDomain>, BookDomain, Int>, VersesUi>(resourceProvider),
    VersesDomainToUiMapper<VersesUi> {

    override fun map(data: Triple<List<VerseDomain>, BookDomain, Int>) = VersesUi.Base(
        ArrayList(data.first.map { verse -> verse.map(mapper) }),
        data.second.map(BookDomainToUiMapper.Name(resourceProvider, R.string.book_and_chapter, data.third))
    )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(mutableListOf(VerseUi.Fail(it)), it)
    }
}