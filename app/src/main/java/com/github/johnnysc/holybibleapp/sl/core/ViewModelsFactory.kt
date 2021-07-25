package com.github.johnnysc.holybibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.johnnysc.holybibleapp.presentation.books.BooksViewModel
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersViewModel
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesViewModel
import com.github.johnnysc.holybibleapp.presentation.main.MainViewModel
import com.github.johnnysc.holybibleapp.presentation.verses.VersesViewModel
import com.github.johnnysc.holybibleapp.sl.books.BooksModule
import com.github.johnnysc.holybibleapp.sl.chapters.ChaptersModule
import com.github.johnnysc.holybibleapp.sl.languages.LanguagesModule
import com.github.johnnysc.holybibleapp.sl.verses.VersesModule
import java.lang.IllegalStateException

/**
 * @author Asatryan on 15.07.2021
 **/
class ViewModelsFactory(
    private val coreModule: CoreModule,
    private val booksModule: BooksModule,
    private val chaptersModule: ChaptersModule,
    private val versesModule: VersesModule,
    private val languagesModule: LanguagesModule
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val module = when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> coreModule
            modelClass.isAssignableFrom(BooksViewModel::class.java) -> booksModule
            modelClass.isAssignableFrom(ChaptersViewModel::class.java) -> chaptersModule
            modelClass.isAssignableFrom(VersesViewModel::class.java) -> versesModule
            modelClass.isAssignableFrom(LanguagesViewModel::class.java) -> languagesModule
            else -> throw IllegalStateException("unknown viewModelClass $modelClass")
        }
        return module.getViewModel() as T
    }
}