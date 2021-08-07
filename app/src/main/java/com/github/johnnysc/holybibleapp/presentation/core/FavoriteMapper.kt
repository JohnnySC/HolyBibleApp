package com.github.johnnysc.holybibleapp.presentation.core

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.books.BookUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUiMapper

/**
 * @author Asatryan on 12.07.2021
 **/
interface FavoriteMapper : Abstract.Mapper.Data<Boolean, Unit>, BookUiMapper<Unit>,
    ChapterUiMapper<Unit>, VerseUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
        map(isFavoriteOrCollapsed)

    override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = map(isFavorite)
}