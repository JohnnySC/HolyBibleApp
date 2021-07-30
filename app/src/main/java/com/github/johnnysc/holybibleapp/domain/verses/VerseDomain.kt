package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Matcher

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDomain {
    fun <T> map(mapper: VerseDomainToUiMapper<T>): T

    data class Base(private val id: Int, private val text: String) : VerseDomain {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>) = mapper.map(id, text)
    }

    object Next : VerseDomain, Matcher<Pair<Int, String>> {
        override fun <T> map(mapper: VerseDomainToUiMapper<T>): T = mapper.map(ID, TEXT)
        override fun matches(arg: Pair<Int, String>) = arg.first == ID && arg.second == TEXT

        private const val ID = -200
        private const val TEXT = "next chapter text"
    }
}