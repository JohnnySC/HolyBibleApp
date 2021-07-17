package com.github.johnnysc.holybibleapp.presentation.verses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 17.07.2021
 **/
class VersesFragment : BaseFragment() {

    private val viewModelFactory by lazy {
        (requireActivity().application as BibleApp).versesFactory()
    }
    private val viewModel by viewModels<VersesViewModel> { viewModelFactory }

    override fun getTitle() = viewModel.getTitle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersesAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchVerses()
        })

        viewModel.observeVerses(this, {
            adapter.update(it)
        })
        recyclerView?.adapter = adapter

        viewModel.init()
    }
}