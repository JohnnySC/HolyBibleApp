package com.github.johnnysc.holybibleapp.presentation.chapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 13.07.2021
 **/
class ChaptersFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).chaptersFactory()
    }
    private val viewModel by viewModels<ChaptersViewModel> { viewModelFactory }

    override fun getTitle() = viewModel.getBookName()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchChapters()
            },
            object : ChaptersAdapter.ChapterClickListener {
                override fun show(item: ChapterUi) = item.open(viewModel)
            })
        viewModel.observeChapters(this, {
            adapter.update(it)
        })
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}