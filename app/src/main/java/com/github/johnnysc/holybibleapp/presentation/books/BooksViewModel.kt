package com.github.johnnysc.holybibleapp.presentation.books

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.domain.books.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 27.06.2021
 **/
class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper:  BooksDomainToUiMapper<BooksUi>,
    private val communication:  BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Pair<Int, String>>,
    private val navigator : BooksNavigator,
    private val navigationCommunication: NavigationCommunication
) : ViewModel(), ShowBook {

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) = communication.map(uiDataCache.changeState(id))

    override fun show(id: Int, name: String) {
        bookCache.save(Pair(id, name))
        navigator.nextScreen(navigationCommunication)
    }

    fun save() = uiDataCache.saveState()

    fun init() {
        navigator.saveBooksScreen()
        fetchBooks()
    }
}