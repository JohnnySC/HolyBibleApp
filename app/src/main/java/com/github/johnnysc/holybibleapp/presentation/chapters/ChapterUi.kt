package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.ComparableTextMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChapterUi : ComparableTextMapper<ChapterUi> {

    class Base(
        private val id: Int, //todo use to get verses
        private val text: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    class Fail(
        private val message: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi() {
        override fun map(mapper: TextMapper) = Unit
    }
}