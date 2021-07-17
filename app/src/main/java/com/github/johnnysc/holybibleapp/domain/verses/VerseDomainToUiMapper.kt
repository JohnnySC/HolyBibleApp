package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUi

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDomainToUiMapper : Abstract.Mapper {

    fun map(id: Int, text: String): VerseUi
}