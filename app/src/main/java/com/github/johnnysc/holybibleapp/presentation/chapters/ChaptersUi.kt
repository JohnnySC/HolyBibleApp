package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ListMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersUi {

    abstract fun map(list: ListMapper<ChapterUi>, text: TextMapper)

    class Base(
        private val chapters: List<ChapterUi>,
        private val bookName: Abstract.Object<Unit, TextMapper>
    ) : ChaptersUi() {
        override fun map(list: ListMapper<ChapterUi>, text: TextMapper) {
            list.map(chapters)
            bookName.map(text)
        }
    }
}