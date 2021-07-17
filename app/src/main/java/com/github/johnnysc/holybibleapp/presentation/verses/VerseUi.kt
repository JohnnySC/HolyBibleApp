package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ComparableTextMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VerseUi : ComparableTextMapper<VerseUi> {

    class Base(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    object Progress : VerseUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}