package com.github.johnnysc.holybibleapp.core

import android.app.Application
import com.github.johnnysc.holybibleapp.data.BooksRepository
import com.github.johnnysc.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.BooksInteractor

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository : BooksRepository = TODO("merge")
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
    }
}