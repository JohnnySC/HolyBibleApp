package com.github.johnnysc.holybibleapp.presentation.books

import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.core.Show
import com.github.johnnysc.holybibleapp.domain.books.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.core.FeatureNavigation
import com.github.johnnysc.holybibleapp.presentation.core.FeatureNavigator
import com.github.johnnysc.holybibleapp.presentation.main.BaseViewModel
import com.github.johnnysc.holybibleapp.sl.core.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 27.06.2021
 **/
class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper<BooksUi>,
    communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Int>,
    private val navigation: FeatureNavigation,
    resourceProvider: ResourceProvider,
    private val clearBooks: () -> Unit,
) : BaseViewModel<BooksCommunication, BooksUi>(resourceProvider, communication),
    Show<Int>, ChangeFavorite<Int>, Save<Unit> {

    private var clear: Boolean = true

    override fun titleResId() = R.string.app_name

    override fun fetch() {
        communication.map(BooksUi.Base(mutableListOf(BookUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) { communication.map(resultUi) }
        }
    }

    fun collapseOrExpand(item: BookUi) {
        communication.map(BooksUi.Base(uiDataCache.changeState(item)))
        save(Unit)
    }

    override fun open(id: Int) {
        bookCache.save(id)
        clear = false
        navigation.showNextScreen()
    }

    override fun save(data: Unit) = uiDataCache.saveState()

    override fun saveScrollPosition(position: Int) =
        booksInteractor.saveScrollPosition(Feature.BOOKS, position)

    override fun scrollPosition() = booksInteractor.scrollPosition(Feature.BOOKS)

    fun init() {
        navigation.init()
        fetch()
    }

    override fun onCleared() {
        super.onCleared()
        if (clear) clearBooks()
    }

    override fun changeFavorite(id: Int) {
        super.changeFavorite(id, communication, booksInteractor, uiDataCache)
    }
}