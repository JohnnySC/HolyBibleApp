package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ListMapper
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesUi : ChangeFavorite<Int> {

    abstract fun map(list: ListMapper<VerseUi>, text: TextMapper)

    class Base(
        private val data: MutableList<VerseUi>,
        private val title: String
    ) : VersesUi() {
        override fun map(list: ListMapper<VerseUi>, text: TextMapper) {
            list.map(data)
            text.map(title)
        }

        override fun changeFavorite(id: Int) {
            val itemToChange = data.find {
                it.matches(id)
            } ?: VerseUi.Empty
            val newItem = itemToChange.changeState()
            data[data.indexOf(itemToChange)] = newItem
        }
    }
}