package com.github.johnnysc.holybibleapp.presentation.deeplink

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BuildString
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUi
import com.github.johnnysc.holybibleapp.presentation.verses.VersesUi

/**
 * @author Asatryan on 04.08.2021
 **/
class DeeplinkVersesDomainToUiMapper(
    private val verseIdContainer: Read<Int>,
    private val mapper: VerseDomainToUiMapper<VerseUi>,
    private val resourceProvider: ResourceProvider
) : VersesDomainToUiMapper<VersesUi>(resourceProvider) {

    override fun map(data: Triple<List<VerseDomain>, BuildString, Int>): VersesUi {
        val list = mutableListOf<VerseUi>()
        data.first.find {
            it.matches(verseIdContainer.read())
        }?.let {
            list.add(it.map(mapper))
        }
        return VersesUi.Base(
            list,
            data.second.build(resourceProvider, R.string.book_and_chapter, data.third)
        )
    }

    override fun map(errorType: ErrorType) = errorMessage(errorType).let {
        VersesUi.Base(mutableListOf(VerseUi.Fail(it)), it)
    }
}
