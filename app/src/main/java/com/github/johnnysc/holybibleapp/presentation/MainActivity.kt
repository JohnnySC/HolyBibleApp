package com.github.johnnysc.holybibleapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.Screens.Companion.BOOKS_SCREEN
import com.github.johnnysc.holybibleapp.presentation.Screens.Companion.CHAPTERS_SCREEN
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.presentation.books.BooksFragment
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersFragment
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as BibleApp).mainViewModel

        viewModel.observe(this, {
            val fragment = when (it) {
                BOOKS_SCREEN -> BooksFragment()
                CHAPTERS_SCREEN -> ChaptersFragment()
                else -> throw IllegalStateException("screen id undefined $it")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        })
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}