package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.presentation.verses.VersesUi

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesDomain : Abstract.Object<VersesUi, VersesDomainToUiMapper> {

    data class Success(private val list: List<VerseDomain>) : VersesDomain() {
        override fun map(mapper: VersesDomainToUiMapper) = mapper.map(list)
    }

    data class Fail(private val errorType: ErrorType) : VersesDomain() {
        override fun map(mapper: VersesDomainToUiMapper) = mapper.map(errorType)
    }
}