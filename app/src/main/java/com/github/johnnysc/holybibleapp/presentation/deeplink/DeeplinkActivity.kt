package com.github.johnnysc.holybibleapp.presentation.deeplink

import android.os.Bundle
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.main.BaseActivity

/**
 * @author Asatryan on 04.08.2021
 */
class DeeplinkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = viewModel(DeeplinkViewModel::class.java, this)
        viewModel.observe(this, {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DeeplinkVerseFragment())
                .commit()
        })
        viewModel.init(intent)
    }
}