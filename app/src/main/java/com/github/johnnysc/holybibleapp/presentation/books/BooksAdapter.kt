package com.github.johnnysc.holybibleapp.presentation.books

import android.view.View
import android.view.ViewGroup
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.*

/**
 * @author Asatryan on 27.06.2021
 **/
class BooksAdapter(
    private val retry: Retry,
    private val collapseListener: CollapseListener,
    private val bookListener: ClickListener<BookUi>,
    private val favoriteListener: Show<Int>,
) : BaseAdapter<BookUi, BaseViewHolder<BookUi>>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> BooksViewHolder.Base(
            R.layout.text_layout.makeView(parent),
            bookListener,
            favoriteListener
        )
        1 -> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BooksViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        3 -> BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        else -> throw IllegalStateException("unknown viewType $viewType")
    }

    abstract class BooksViewHolder(view: View) : BaseViewHolder<BookUi>(view) {

        abstract class Info(view: View) : BooksViewHolder(view) {
            protected val name: CustomTextView = itemView.findViewById(R.id.textView)
            override fun bind(item: BookUi) = item.map(name)
        }

        class Base(
            view: View,
            private val bookListener: ClickListener<BookUi>,
            private val favoriteListener: Show<Int>
        ) : Info(view) {
            private val reveal: SwipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout)
            private val backgroundView: CustomFrameLayout =
                itemView.findViewById(R.id.backgroundView)
            private val favoriteButton =
                itemView.findViewById<FavoriteView>(R.id.changeFavoriteView)
            private val favoriteLayout =
                itemView.findViewById<View>(R.id.changeFavoriteLayout)

            override fun bind(item: BookUi) {
                super.bind(item)
                reveal.close(false)
                item.mapFavorite(backgroundView)
                item.mapFavorite(favoriteButton)
                favoriteLayout.setOnClickListener {
                    item.open(favoriteListener)
                    reveal.close(true)
                }
                name.setOnClickListener {
                    bookListener.click(item)
                }
            }
        }

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<CollapseView>(R.id.collapseView)
            override fun bind(item: BookUi) {
                super.bind(item)
                itemView.setOnClickListener {
                    item.collapseOrExpand(collapse)
                }
                item.showCollapsed(collapseView)
            }
        }
    }

    interface CollapseListener {
        fun collapseOrExpand(id: Int)
    }
}