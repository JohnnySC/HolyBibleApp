package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.Same
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper

/**
 * @author Asatryan on 02.08.2021
 **/
interface BookUiMapper<T> {

    fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean): T

    class Text(private val textMapper: TextMapper) : BookUiMapper<Unit> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
            textMapper.map(name)
    }

    class Id(private val id: Int) : BookUiMapper<Boolean> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) = this.id == id
    }

    class Display(private val show: Show<Int>) : BookUiMapper<Unit> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) = show.open(id)
    }

    class ChangeBookState : BookUiMapper<BookUi> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
            BookUi.Base(id, name, !isFavoriteOrCollapsed)
    }

    class ChangeTestamentState : BookUiMapper<BookUi> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
            BookUi.Testament(id, name, !isFavoriteOrCollapsed)
    }

    class CollapsedState : BookUiMapper<Boolean> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
            isFavoriteOrCollapsed
    }

    interface Compare : BookUiMapper<Boolean>, Same<BookUi> {

        class Base : Compare {
            private var itemToCompare: BookUi = BookUi.Empty

            override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
                itemToCompare.map(Id(id))

            override fun itemToCompare(item: BookUi) {
                itemToCompare = item
            }
        }
    }

    class Store(private val saveId: Save<Int>) : BookUiMapper<Unit> {
        override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) = saveId.save(id)
    }
}