package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.Same
import com.github.johnnysc.holybibleapp.core.Show

/**
 * @author Asatryan on 03.08.2021
 **/
interface VerseUiMapper<T> {

    fun map(id: Int, text: String, isFavorite: Boolean): T

    class Display(private val show: Show<Int>) : VerseUiMapper<Unit> {
        override fun map(id: Int, text: String, isFavorite: Boolean) = show.open(id)
    }

    class ChangeState : VerseUiMapper<VerseUi> {
        override fun map(id: Int, text: String, isFavorite: Boolean) =
            VerseUi.Base(id, text, !isFavorite)
    }

    interface Compare : VerseUiMapper<Boolean>, Same<VerseUi> {

        class Base : Compare {
            private var itemToCompare: VerseUi = VerseUi.Empty

            override fun map(id: Int, text: String, isFavorite: Boolean) =
                itemToCompare.map(Id(id))

            override fun itemToCompare(item: VerseUi) {
                itemToCompare = item
            }
        }
    }

    class Id(private val id: Int) : VerseUiMapper<Boolean> {
        override fun map(id: Int, text: String, isFavorite: Boolean) = this.id == id
    }
}