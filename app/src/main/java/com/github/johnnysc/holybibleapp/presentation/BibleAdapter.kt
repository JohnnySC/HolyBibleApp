package com.github.johnnysc.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Book

/**
 * @author Asatryan on 27.06.2021
 **/
class BibleAdapter : RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<Book>()

    fun update(new: List<Book>) {
        books.clear()
        books.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_layout, parent, false)
        // TODO: 27.06.2021 progress and fail
        return BibleViewHolder(view)
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) =
        holder.bind(books[position])

    override fun getItemCount() = books.size

    inner class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(book: Book) {
            itemView.findViewById<TextView>(R.id.textView).text = book.name
        }
    }
}