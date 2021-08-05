package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Matcher

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDomain : Matcher<Int> {
    fun <T> map(mapper: VerseDomainToUiMapper<T>): T

    data class Base(
        private val id: Int,
        private val visibleId: Int,
        private val text: String,
        private val isFavorite: Boolean = false
    ) : VerseDomain {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>) =
            mapper.map(id, visibleId, text, isFavorite)

        override fun matches(arg: Int) = arg == visibleId
    }

    object Next : VerseDomain {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>): T = mapper.map(ID, ID, TEXT)
        override fun matches(arg: Int) = arg == ID

        private const val ID = -200
        private const val TEXT = "next chapter text"
    }
}