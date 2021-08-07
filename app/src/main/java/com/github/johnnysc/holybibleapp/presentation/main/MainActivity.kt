package com.github.johnnysc.holybibleapp.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.github.johnnysc.holybibleapp.R

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModel(MainViewModel::class.java, this)

        viewModel.observe(this, { navigate(viewModel.fragment(it)) })
        viewModel.init(savedInstanceState == null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        if (item.itemId == R.id.languages) {
            viewModel.showLanguagesScreen()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigate(fragment: BaseFragment<*>) = with(supportFragmentManager) {
        if (canReplace(fragment))
            beginTransaction().replace(R.id.container, fragment, fragment.name()).commit()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack()) super.onBackPressed()
    }
}

private fun FragmentManager.canReplace(fragment: BaseFragment<*>) =
    fragments.isEmpty() || !(fragments.last() as BaseFragment<*>).matches(fragment.name())