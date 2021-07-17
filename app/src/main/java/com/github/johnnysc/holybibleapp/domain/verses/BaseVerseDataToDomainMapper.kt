package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.verses.VerseDataToDomainMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVerseDataToDomainMapper : VerseDataToDomainMapper {
    override fun map(verseId: Int, text: String) = VerseDomain(verseId, text)
}