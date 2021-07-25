package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(mapper: VersesDataToDomainMapper<T>, title: String): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(mapper: VersesDataToDomainMapper<T>, title: String) =
            mapper.map(Pair(verses, title))
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(mapper: VersesDataToDomainMapper<T>, title: String) = mapper.map(e)
    }
}