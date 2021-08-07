package com.github.johnnysc.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.core.ClickListener
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 17.07.2021
 **/
abstract class VersesFragment<T : VersesViewModel> : BaseFragment<T>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetch()
            },
            object : ClickListener<VerseUi> {
                override fun click(item: VerseUi) = viewModel.showNextChapterVerses()
            },
            object : Show<Int> {
                override fun open(id: Int) = viewModel.changeFavorite(id)
            },
            object : ClickListener<VerseUi> {
                override fun click(item: VerseUi) = ShareVerse.Base(
                    getString(R.string.share_verse),
                    viewModel.share(item)
                ).map(requireActivity() as Share)
            },
        )

        viewModel.observe(this, { ui ->
            ui.map(adapter, title())
            scrollTo()
        })
        setAdapter(adapter)

        viewModel.init()
    }

    class Base : VersesFragment<VersesViewModel.Base>() {
        override fun viewModelClass() = VersesViewModel.Base::class.java
    }
}