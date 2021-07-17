package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUi

/**
 * @author Asatryan on 17.07.2021
 **/
data class VerseDomain(private val id: Int, private val text: String) :
    Abstract.Object<VerseUi, VerseDomainToUiMapper> {
    override fun map(mapper: VerseDomainToUiMapper) = mapper.map(id, text)
}