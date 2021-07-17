package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDataToDomainMapper : Abstract.Mapper {
    fun map(verseId: Int, text: String): VerseDomain
}