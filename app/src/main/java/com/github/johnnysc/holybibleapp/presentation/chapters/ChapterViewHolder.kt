package com.github.johnnysc.holybibleapp.presentation.chapters

import android.view.View
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.presentation.core.BaseViewHolder
import com.github.johnnysc.holybibleapp.presentation.core.ClickListener
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomFrameLayout
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomTextView
import com.github.johnnysc.holybibleapp.presentation.core.view.FavoriteView

/**
 * @author Asatryan on 12.07.2021
 **/
abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

    class Base(
        view: View,
        clickListener: ClickListener<ChapterUi>,
        private val favoriteListener: Show<Pair<Int, Int>>,
    ) : BaseViewHolder.Base<ChapterUi>(view, clickListener) {

        override fun map(
            item: ChapterUi,
            background: CustomFrameLayout,
            button: FavoriteView,
            text: CustomTextView,
        ) = with(item) {
            map(background)
            map(button)
            map(text)
        }

        override fun mapFavorite(item: ChapterUi) {
            item.map(ChapterUiMapper.Display(favoriteListener))
        }
    }

    class Error(view: View, retry: Retry) : Fail<ChapterUi>(view, retry) {
        override fun mapErrorMessage(item: ChapterUi, textMapper: TextMapper) =
            item.map(textMapper)
    }
}