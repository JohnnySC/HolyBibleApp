package com.github.johnnysc.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.presentation.BibleAdapter
import com.github.johnnysc.holybibleapp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as BibleApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BibleAdapter(
            object : BibleAdapter.Retry {
                override fun tryAgain() = viewModel.fetchBooks()
            },
            object : BibleAdapter.CollapseListener {
                override fun collapseOrExpand(id: Int) = viewModel.collapseOrExpand(id)
            })
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel.observe(this, {
            adapter.update(it)
        })
        viewModel.fetchBooks()
    }

    override fun onPause() {
        viewModel.saveCollapsedStates()
        super.onPause()
    }
}