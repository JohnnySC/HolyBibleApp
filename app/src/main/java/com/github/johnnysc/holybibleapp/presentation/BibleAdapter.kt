package com.github.johnnysc.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.R

/**
 * @author Asatryan on 27.06.2021
 **/
class BibleAdapter(private val retry: Retry, private val collapseListener: CollapseListener) :
    RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(new: List<BookUi>) {
        val diffCallback = DiffUtilCallback(books, new)
        val result = DiffUtil.calculateDiff(diffCallback)
        books.clear()
        books.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (books[position]) {
        is BookUi.Base -> 0
        is BookUi.Fail -> 1
        is BookUi.Testament -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> BibleViewHolder.Base(R.layout.book_layout.makeView(parent))
        1 -> BibleViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        2 -> BibleViewHolder.Testament(R.layout.testament.makeView(parent), collapseListener)
        else -> BibleViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) =
        holder.bind(books[position])

    override fun getItemCount() = books.size

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(book: BookUi) {
        }

        class FullscreenProgress(view: View) : BibleViewHolder(view)

        abstract class Info(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Base(view: View) : Info(view)

        class Testament(view: View, private val collapse: CollapseListener) : Info(view) {
            private val collapseView = itemView.findViewById<ImageView>(R.id.collapseView)
            override fun bind(book: BookUi) {
                super.bind(book)
                itemView.setOnClickListener {
                    book.collapseOrExpand(collapse)
                }
                book.showCollapsed(object : BookUi.CollapseMapper {
                    override fun show(collapsed: Boolean) {
                        val iconId: Int = if (collapsed) {
                            R.drawable.ic_expand_more_24
                        } else {
                            R.drawable.ic_expand_less_24
                        }
                        collapseView.setImageResource(iconId)
                    }
                })
            }
        }

        class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })
                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }

    interface Retry {
        fun tryAgain()
    }

    interface CollapseListener {
        fun collapseOrExpand(id: Int)
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)