package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.presentation.books.ChangeState
import com.github.johnnysc.holybibleapp.presentation.books.MapFavorite

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VerseUi : ComparableTextMapper<VerseUi>, MapFavorite, ChangeState<VerseUi>, Open<Int>,
    Matcher<Int> {

    override fun map(mapper: TextMapper) = Unit
    override fun changeState(): VerseUi = Empty
    override fun open(show: Show<Int>) = Unit
    override fun matches(arg: Int) = false

    abstract class Abstract(private val text: String) : VerseUi() {
        override fun map(mapper: TextMapper) = mapper.map(text)
    }

    data class Base(
        private val id: Int,
        private val text: String,
        private val isFavorite: Boolean
    ) : Abstract(text) {

        override fun open(show: Show<Int>) = show.open(id)
        override fun mapFavorite(mapper: FavoriteMapper) = mapper.map(isFavorite)
        override fun matches(arg: Int) = arg == id

        override fun same(item: VerseUi) = item is Base && item.id == id
        override fun changeState() = Base(id, text, !isFavorite)

        override fun sameContent(item: VerseUi) =
            item is Base && item.text == text && isFavorite == item.isFavorite
    }

    class Fail(text: String) : Abstract(text)
    data class Next(private val text: String) : Abstract(text) {
        override fun same(item: VerseUi) = item is Next
        override fun sameContent(item: VerseUi) = true
    }

    object Progress : VerseUi()
    object Empty : VerseUi()
}