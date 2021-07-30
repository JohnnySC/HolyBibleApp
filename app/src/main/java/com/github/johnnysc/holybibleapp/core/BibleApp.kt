package com.github.johnnysc.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.holybibleapp.BuildConfig.USE_MOCKS
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.DependencyContainer
import com.github.johnnysc.holybibleapp.sl.core.ViewModelsFactory

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    private val coreModule = CoreModule(USE_MOCKS)

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule, USE_MOCKS))
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}