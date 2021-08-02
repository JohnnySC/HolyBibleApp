package com.github.johnnysc.holybibleapp.presentation.verses

import android.view.View
import android.view.ViewGroup
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.*

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<VerseUi>,
    private val favoriteListener: Show<Int>
) : BaseAdapter<VerseUi, BaseViewHolder<VerseUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is VerseUi.Base -> 0
        is VerseUi.Fail -> 1
        is VerseUi.Progress -> 2
        is VerseUi.Next -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> VerseViewHolder.Base(R.layout.verse_layout.makeView(parent), favoriteListener)
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        3 -> VerseViewHolder.Next(R.layout.next_layout.makeView(parent), clickListener)
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    abstract class VerseViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {

        class Base(
            view: View,
            private val favoriteListener: Show<Int>
        ) : VerseViewHolder(view) {
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            private val reveal: SwipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout)
            private val backgroundView: CustomFrameLayout =
                itemView.findViewById(R.id.backgroundView)

            private val favoriteButton =
                itemView.findViewById<FavoriteView>(R.id.changeFavoriteView)
            private val favoriteLayout =
                itemView.findViewById<View>(R.id.changeFavoriteLayout)

            override fun bind(item: VerseUi) {
                reveal.close(false)
                item.mapFavorite(backgroundView)
                item.mapFavorite(favoriteButton)
                item.map(textView)
                favoriteLayout.setOnClickListener {
                    item.open(favoriteListener)
                    reveal.close(true)
                }
            }
        }

        class Next(view: View, private val clickListener: ClickListener<VerseUi>) :
            VerseViewHolder(view) {
            private val nextButton = itemView.findViewById<CustomButton>(R.id.nextButton)
            override fun bind(item: VerseUi) {
                item.map(nextButton)
                nextButton.setOnClickListener {
                    clickListener.click(item)
                }
            }
        }
    }
}