package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ListMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesUi {

    abstract fun map(list: ListMapper<VerseUi>, text: TextMapper)

    class Base(
        private val data: List<VerseUi>,
        private val title: String
    ) : VersesUi() {
        override fun map(list: ListMapper<VerseUi>, text: TextMapper) {
            list.map(data)
            text.map(title)
        }
    }
}