package com.github.johnnysc.holybibleapp.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.BibleApp
import com.github.johnnysc.holybibleapp.core.TextMapper
import com.github.johnnysc.holybibleapp.presentation.verses.ShareMapper

/**
 * @author Asatryan on 04.08.2021
 **/
abstract class BaseActivity : AppCompatActivity(), TextMapper, ShareMapper {

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as BibleApp).viewModel(model, owner)

    override fun map(data: String) {
        title = data
    }

    override fun share(share: Intent) = startActivity(share)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}