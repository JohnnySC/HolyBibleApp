package com.github.johnnysc.holybibleapp.presentation.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 13.07.2021
 **/
class BooksFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).booksFactory()
    }
    private val viewModel by activityViewModels<BooksViewModel> { viewModelFactory }

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
                override fun showBook(id: Int, name: String) = viewModel.showBook(id, name)
            })
        recyclerView?.adapter = adapter
        viewModel.observe(this, {
            adapter.update(it)
        })
        viewModel.init()
    }

    override fun onPause() {
        viewModel.save()
        super.onPause()
    }
}