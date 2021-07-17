package com.github.johnnysc.holybibleapp.presentation.verses

import android.view.View
import android.view.ViewGroup
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BaseAdapter
import com.github.johnnysc.holybibleapp.core.BaseViewHolder
import com.github.johnnysc.holybibleapp.core.CustomTextView
import com.github.johnnysc.holybibleapp.core.Retry

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesAdapter(private val retry: Retry) : BaseAdapter<VerseUi, BaseViewHolder<VerseUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is VerseUi.Base -> 0
        is VerseUi.Fail -> 1
        is VerseUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> VerseViewHolder.Base(R.layout.text_layout.makeView(parent))
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    abstract class VerseViewHolder(view: View) : BaseViewHolder<VerseUi>(view) {

        class Base(view: View) : VerseViewHolder(view) {
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: VerseUi) = item.map(textView)
        }
    }
}