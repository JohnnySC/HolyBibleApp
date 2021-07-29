package com.github.johnnysc.holybibleapp.presentation.books

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.domain.books.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 27.06.2021
 **/
class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper<BooksUi>,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Int>,
    private val navigator: BooksNavigator,
    private val navigationCommunication: NavigationCommunication,
    resourceProvider: ResourceProvider
) : BaseViewModel(resourceProvider), Show {

    override fun getTitleResId() = R.string.app_name

    fun fetchBooks() {
        communication.map(BooksUi.Base(listOf(BookUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BooksUi>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) = communication.map(BooksUi.Base(uiDataCache.changeState(id)))

    override fun open(id: Int) {
        bookCache.save(id)
        navigator.nextScreen(navigationCommunication)
    }

    fun save() = uiDataCache.saveState()

    override fun saveScrollPosition(position: Int) = booksInteractor.saveScrollPosition(position)
    override fun scrollPosition() = booksInteractor.scrollPosition()

    fun init() {
        navigator.saveBooksScreen()
        fetchBooks()
    }
}