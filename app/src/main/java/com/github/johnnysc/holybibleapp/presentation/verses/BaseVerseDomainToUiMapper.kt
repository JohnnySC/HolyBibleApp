package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.domain.verses.VerseDomainToUiMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVerseDomainToUiMapper : VerseDomainToUiMapper {
    override fun map(id: Int, text: String) = VerseUi.Base("$id $text")
}