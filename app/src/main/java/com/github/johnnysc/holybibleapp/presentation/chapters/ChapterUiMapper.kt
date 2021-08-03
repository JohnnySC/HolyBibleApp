package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.Same
import com.github.johnnysc.holybibleapp.core.Show

/**
 * @author Asatryan on 03.08.2021
 **/
interface ChapterUiMapper<T> {

    fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean): T

    class Display(private val show: Show<Pair<Int, Int>>) : ChapterUiMapper<Unit> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
            show.open(Pair(visibleId, id))
    }

    class Id(private val id: Int) : ChapterUiMapper<Boolean> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = id == this.id
    }

    interface Compare : ChapterUiMapper<Boolean>, Same<ChapterUi> {

        class Base : Compare {
            private var itemToCompare: ChapterUi = ChapterUi.Empty

            override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
                itemToCompare.map(Id(id))

            override fun itemToCompare(item: ChapterUi) {
                itemToCompare = item
            }
        }
    }

    class ChangeState : ChapterUiMapper<ChapterUi> {
        override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) =
            ChapterUi.Base(visibleId, id, text, !isFavorite)
    }
}