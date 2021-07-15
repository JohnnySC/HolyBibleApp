package com.github.johnnysc.holybibleapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BibleApp

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as BibleApp).getMainViewModel()

        viewModel.observe(this, {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, viewModel.getFragment(it))
                .commit()
        })
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }
}