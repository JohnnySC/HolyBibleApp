package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ComparableTextMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VerseUi : ComparableTextMapper<VerseUi> {

    abstract class Abstract(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Base(text: String) : Abstract(text)
    class Fail(text: String) : Abstract(text)
    class Next(text: String) : Abstract(text)

    object Progress : VerseUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}