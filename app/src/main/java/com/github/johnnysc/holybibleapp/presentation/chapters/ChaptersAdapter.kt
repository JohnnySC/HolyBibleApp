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
    private val clickListener: ClickListener<ChapterUi>
) : BaseAdapter<ChapterUi, BaseViewHolder<ChapterUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ChapterUi.Base -> 0
        is ChapterUi.Fail -> 1
        is ChapterUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> ChapterViewHolder.Base(R.layout.text_layout.makeView(parent), clickListener)
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    abstract class ChapterViewHolder(view: View) : BaseViewHolder<ChapterUi>(view) {

        class Base(
            view: View,
            private val clickListener: ClickListener<ChapterUi>
        ) : ChapterViewHolder(view) {
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: ChapterUi) {
                item.map(textView)
                itemView.setOnClickListener {
                    clickListener.click(item)
                }
            }
        }
    }
}