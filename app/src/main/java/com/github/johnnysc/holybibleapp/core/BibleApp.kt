package com.github.johnnysc.holybibleapp.core

import android.app.Application
import com.github.johnnysc.holybibleapp.domain.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.BaseBooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.BooksCommunication
import com.github.johnnysc.holybibleapp.presentation.MainViewModel
import com.github.johnnysc.holybibleapp.presentation.ResourceProvider

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}