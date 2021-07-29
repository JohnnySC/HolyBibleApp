package com.github.johnnysc.holybibleapp.presentation.chapters

import android.os.Bundle
import android.view.View
import com.github.johnnysc.holybibleapp.core.ClickListener
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 13.07.2021
 **/
class ChaptersFragment : BaseFragment<ChaptersViewModel>() {

    override fun viewModelClass() = ChaptersViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChaptersAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchChapters()
            },
            object : ClickListener<ChapterUi> {
                override fun click(item: ChapterUi) = item.open(viewModel)
            })
        viewModel.observeChapters(this, { ui ->
            ui.map(adapter, title())
            scrollTo()
        })
        setAdapter(adapter)

        viewModel.init()
    }
}