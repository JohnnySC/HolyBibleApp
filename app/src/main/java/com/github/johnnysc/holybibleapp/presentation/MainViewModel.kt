package com.github.johnnysc.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Asatryan on 27.06.2021
 **/
class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { //todo interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val result: BooksUi = booksInteractor.fetchBooks().map(mapper)
        Dispatchers.Main.run {
            result.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Book>>) {
        communication.observeSuccess(owner, observer)
    }
}