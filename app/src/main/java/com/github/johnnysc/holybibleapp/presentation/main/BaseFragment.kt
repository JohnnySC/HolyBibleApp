package com.github.johnnysc.holybibleapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper

/**
 * @author Asatryan on 13.07.2021
 **/
abstract class BaseFragment<T : BaseViewModel<*, *>> : Fragment(), Matcher<String>,
    BackNavigationUi {

    protected lateinit var viewModel: T
    private var recyclerView: RecyclerView? = null//todo viewbinding
    private var hasScrolled = false
    private lateinit var layoutManager: LinearLayoutManager

    protected abstract fun viewModelClass(): Class<T>

    override fun showBack(): Boolean = true

    override fun matches(arg: String) = name() == arg
    fun name(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as BaseActivity).viewModel(viewModelClass(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(layoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            showBack()
        )
        title().map(viewModel.title())
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveScrollPosition(layoutManager.findLastCompletelyVisibleItemPosition())
    }

    protected open fun layoutResId() = R.layout.fragment_main
    protected fun title() = requireActivity() as TextMapper
    protected fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView?.adapter = adapter
    }
    protected fun scrollTo(position: Int = viewModel.scrollPosition()) {
        if (itemsCount() > position && position > 0 && !hasScrolled) {
            recyclerView?.smoothScrollToPosition(position)
            hasScrolled = true
        }
    }
    private fun itemsCount() = recyclerView?.adapter?.itemCount ?: 0
}