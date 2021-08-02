package com.github.johnnysc.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import com.github.johnnysc.holybibleapp.core.ClickListener
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesFragment : BaseFragment<VersesViewModel>() {

    override fun viewModelClass() = VersesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchVerses()
            },
            object : ClickListener<VerseUi> {
                override fun click(item: VerseUi) = viewModel.showNextChapterVerses()
            },
            object : Show<Int> {
                override fun open(id: Int) = viewModel.changeFavorite(id)
            }
        )

        viewModel.observeVerses(this, { ui ->
            ui.map(adapter, title())
            scrollTo()
        })
        setAdapter(adapter)

        viewModel.init()
    }
}