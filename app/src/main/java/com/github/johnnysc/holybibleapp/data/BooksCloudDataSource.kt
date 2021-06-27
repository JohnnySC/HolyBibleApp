package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.net.BookCloud
import com.github.johnnysc.holybibleapp.data.net.BooksService

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {
        override suspend fun fetchBooks() = service.fetchBooks()
    }
}