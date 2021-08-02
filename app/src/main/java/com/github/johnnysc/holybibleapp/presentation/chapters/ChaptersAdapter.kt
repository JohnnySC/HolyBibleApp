package com.github.johnnysc.holybibleapp.presentation.chapters

import android.view.View
import android.view.ViewGroup
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.*

/**
 * @author Asatryan on 12.07.2021
 **/
class ChaptersAdapter(
    private val retry: Retry,
    private val clickListener: ClickListener<ChapterUi>,
    private val favoriteListener: Show<Pair<Int, Int>>,
) : BaseAdapter<ChapterUi, BaseViewHolder<ChapterUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ChapterUi.Base -> 0
        is ChapterUi.Fail -> 1
        is ChapterUi.Progress -> 2
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> ChapterViewHolder.Base(
            R.layout.text_layout.makeView(parent),
            clickListener,
            favoriteListener
        )
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

        class Base(
            view: View,
            private val clickListener: ClickListener<ChapterUi>,
            private val favoriteListener: Show<Pair<Int, Int>>
        ) : ChapterViewHolder(view) {
            private val reveal: SwipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout)
            private val backgroundView: CustomFrameLayout =
                itemView.findViewById(R.id.backgroundView)
            private val favoriteButton =
                itemView.findViewById<FavoriteView>(R.id.changeFavoriteView)
            private val favoriteLayout =
                itemView.findViewById<View>(R.id.changeFavoriteLayout)

            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: ChapterUi) {
                reveal.close(false)
                item.mapFavorite(backgroundView)
                item.mapFavorite(favoriteButton)
                item.map(textView)
                textView.setOnClickListener {
                    clickListener.click(item)
                }
                favoriteLayout.setOnClickListener {
                    item.open(favoriteListener)
                    reveal.close(true)
                }
            }
        }
    }
}