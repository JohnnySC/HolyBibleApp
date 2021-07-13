package com.github.johnnysc.holybibleapp.presentation.books

import android.os.Bundle
import android.view.View
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.BaseFragment

/**
 * @author Asatryan on 13.07.2021
 **/
class BooksFragment : BaseFragment() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as BibleApp).booksViewModel
    }

    override fun getTitle() = getString(R.string.app_name)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchBooks()
            },
            object : BooksAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : BooksAdapter.BookListener {
                override fun showBook(id: Int, name:String) = viewModel.showBook(id, name)
            })
        recyclerView?.adapter = adapter
        viewModel.observe(this, {
            adapter.update(it)
        })
        viewModel.init()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCollapsedStates()
    }
}