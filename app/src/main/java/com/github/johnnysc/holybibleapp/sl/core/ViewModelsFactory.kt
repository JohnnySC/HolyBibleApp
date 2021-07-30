package com.github.johnnysc.holybibleapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.johnnysc.holybibleapp.presentation.books.BooksViewModel
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersViewModel
import com.github.johnnysc.holybibleapp.presentation.languages.LanguagesViewModel
import com.github.johnnysc.holybibleapp.presentation.main.MainViewModel
import com.github.johnnysc.holybibleapp.presentation.verses.VersesViewModel

/**
 * @author Asatryan on 15.07.2021
 **/
class ViewModelsFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    private val map = HashMap<Class<*>, Feature>().apply {
        put(MainViewModel::class.java, Feature.MAIN)
        put(BooksViewModel::class.java, Feature.BOOKS)
        put(ChaptersViewModel::class.java, Feature.CHAPTERS)
        put(VersesViewModel::class.java, Feature.VERSES)
        put(LanguagesViewModel::class.java, Feature.LANGUAGES)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}