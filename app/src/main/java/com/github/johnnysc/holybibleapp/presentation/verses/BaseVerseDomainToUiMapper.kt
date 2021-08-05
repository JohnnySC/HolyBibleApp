package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomainToUiMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVerseDomainToUiMapper(private val resourceProvider: ResourceProvider) :
    VerseDomainToUiMapper<VerseUi> {
    override fun map(id: Int, visibleId: Int, text: String, isFavorite: Boolean) =
        if (VerseDomain.Next.matches(id))
            VerseUi.Next(resourceProvider.string(R.string.next_chapter))
        else
            VerseUi.Base(id, "$visibleId $text", isFavorite)
}