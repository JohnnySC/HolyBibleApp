package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.verses.VersesDomain

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesData : Abstract.Object<VersesDomain, VersesDataToDomainMapper> {

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper) = mapper.map(verses)
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun map(mapper: VersesDataToDomainMapper) = mapper.map(e)
    }
}