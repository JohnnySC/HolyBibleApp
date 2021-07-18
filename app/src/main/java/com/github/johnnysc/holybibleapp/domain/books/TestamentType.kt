package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Matcher

/**
 * @author Asatryan on 03.07.2021
 **/
enum class TestamentType(private val id: Int) : Matcher<Int> {

    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg: Int) = id == arg
    fun <T> map(mapper: BookDomainToUiMapper<T>) = mapper.map(id, name)
}