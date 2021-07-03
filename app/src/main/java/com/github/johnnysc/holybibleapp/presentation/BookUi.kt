package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 03.07.2021
 **/
sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress : BookUi()

    class Base(
        private val id: Int, //todo use for getting chapters
        private val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    class Fail(
        private val message: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    //todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }
}