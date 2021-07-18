package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.verses.VerseDataToDomainMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVerseDataToDomainMapper : VerseDataToDomainMapper<VerseDomain> {
    override fun map(verseId: Int, text: String) = VerseDomain.Base(verseId, text)
}