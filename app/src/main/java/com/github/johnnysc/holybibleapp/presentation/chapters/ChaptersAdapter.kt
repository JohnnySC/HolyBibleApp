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
        1 -> ChapterViewHolder.Error(R.layout.fail_fullscreen.makeView(parent), retry)
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
                item.map(backgroundView)
                item.map(favoriteButton)
                item.map(textView)
                textView.setOnClickListener {
                    clickListener.click(item)
                }
                favoriteLayout.setOnClickListener {
                    item.map(ChapterUiMapper.Display(favoriteListener))
                    reveal.close(true)
                }
            }
        }

        class Error(view: View, retry: Retry) : Fail<ChapterUi>(view, retry) {
            override fun mapErrorMessage(item: ChapterUi, textMapper: TextMapper) =
                item.map(textMapper)
        }
    }

    override fun diffUtilCallback(
        list: ArrayList<ChapterUi>,
        data: List<ChapterUi>
    ) = ChapterDiffUtilCallback(list, data, ChapterUiMapper.Compare.Base())

    inner class ChapterDiffUtilCallback(
        oldList: List<ChapterUi>,
        newList: List<ChapterUi>,
        same: ChapterUiMapper.Compare
    ) : DiffUtilCallback<ChapterUi, ChapterUiMapper.Compare>(oldList, newList, same) {
        override fun same(item: ChapterUi, same: ChapterUiMapper.Compare) = item.map(same)
    }
}