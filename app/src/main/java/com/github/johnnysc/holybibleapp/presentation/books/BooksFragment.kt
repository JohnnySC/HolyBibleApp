package com.github.johnnysc.holybibleapp.presentation.books

import android.os.Bundle
import android.view.View
import com.github.johnnysc.holybibleapp.core.ClickListener
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 13.07.2021
 **/
class BooksFragment : BaseFragment<BooksViewModel>() {

    override fun viewModelClass() = BooksViewModel::class.java
    override fun showBackIcon() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BooksAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchBooks()
            },
            object : BooksAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            },
            object : ClickListener<BookUi> {
                override fun click(item: BookUi) = item.open(viewModel)
            })
        setAdapter(adapter)
        viewModel.observe(this, {
            it.map(adapter)
            scrollTo()
        })
        viewModel.init()
    }

    override fun onPause() {
        viewModel.save()
        super.onPause()
    }
}