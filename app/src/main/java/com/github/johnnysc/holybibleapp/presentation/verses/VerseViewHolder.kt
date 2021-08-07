package com.github.johnnysc.holybibleapp.presentation.verses

import android.view.View
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.presentation.core.BaseViewHolder
import com.github.johnnysc.holybibleapp.presentation.core.ClickListener
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomButton
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomFrameLayout
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomTextView
import com.github.johnnysc.holybibleapp.presentation.core.view.FavoriteView

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VerseViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {

    class Base(
        view: View,
        private val favoriteListener: Show<Int>,
        shareClickListener: ClickListener<VerseUi>,
    ) : BaseViewHolder.Base<VerseUi>(view, shareClickListener) {
        private val shareView = itemView.findViewById<View>(R.id.shareLayout)

        override fun clickableView(): View = shareView

        override fun map(
            item: VerseUi, background: CustomFrameLayout, button: FavoriteView, text: CustomTextView
        ) = with(item) {
            map(background)
            map(button)
            map(text)
        }

        override fun mapFavorite(item: VerseUi) {
            item.map(VerseUiMapper.Display(favoriteListener))
        }
    }

    class Next(view: View, private val clickListener: ClickListener<VerseUi>) :
        VerseViewHolder(view) {
        private val nextButton = itemView.findViewById<CustomButton>(R.id.nextButton)
        override fun bind(item: VerseUi) {
            item.map(nextButton)
            nextButton.setOnClickListener { clickListener.click(item) }
        }
    }

    class Error(view: View, retry: Retry) : Fail<VerseUi>(view, retry) {
        override fun mapErrorMessage(item: VerseUi, textMapper: TextMapper) =
            item.map(textMapper)
    }
}