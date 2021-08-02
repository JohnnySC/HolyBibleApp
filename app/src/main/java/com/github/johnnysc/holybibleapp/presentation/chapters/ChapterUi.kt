package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.*
import com.github.johnnysc.holybibleapp.presentation.books.ChangeState
import com.github.johnnysc.holybibleapp.presentation.books.MapFavorite

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChapterUi : ComparableTextMapper<ChapterUi>, Open<Pair<Int, Int>>, MapFavorite,
    Matcher<Int>, ChangeState<ChapterUi> {

    override fun open(show: Show<Pair<Int, Int>>) = Unit
    override fun matches(arg: Int) = false
    override fun changeState(): ChapterUi = Empty
    override fun map(mapper: TextMapper) = Unit

    class Base(
        private val visibleId: Int,
        private val id: Int,
        private val text: String,
        private val isFavorite: Boolean = false
    ) : ChapterUi() {
        //todo replace all these methods by 1 map with generic mapper
        override fun same(item: ChapterUi) =
            item is Base && item.id == id && item.visibleId == visibleId

        override fun sameContent(item: ChapterUi) =
            item is Base && text == item.text && isFavorite == item.isFavorite

        override fun mapFavorite(mapper: FavoriteMapper) = mapper.map(isFavorite)
        override fun matches(arg: Int) = arg == id
        override fun map(mapper: TextMapper) = mapper.map(text)
        override fun open(show: Show<Pair<Int, Int>>) = show.open(Pair(visibleId, id))
        override fun changeState() = Base(visibleId, id, text, !isFavorite)
    }

    class Fail(
        private val message: String
    ) : ChapterUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
    }

    object Progress : ChapterUi()
    object Empty : ChapterUi()
}