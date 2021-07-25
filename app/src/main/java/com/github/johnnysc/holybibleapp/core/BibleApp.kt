package com.github.johnnysc.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.holybibleapp.BuildConfig.USE_MOCKS
import com.github.johnnysc.holybibleapp.sl.books.BooksModule
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.ViewModelsFactory
import com.github.johnnysc.holybibleapp.sl.languages.LanguagesModule
import com.github.johnnysc.holybibleapp.sl.verses.VersesModule

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    private val coreModule = CoreModule(USE_MOCKS)

    private val booksModule by lazy {
        BooksModule(coreModule, USE_MOCKS)
    }
    private val factory by lazy {
        ViewModelsFactory(
            coreModule,
            booksModule,
            ChaptersModule(coreModule, booksModule, USE_MOCKS),
            VersesModule(coreModule, booksModule, USE_MOCKS),
            LanguagesModule(coreModule)
        )
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> getViewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}