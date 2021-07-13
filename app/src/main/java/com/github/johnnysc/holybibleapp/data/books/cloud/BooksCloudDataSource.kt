package com.github.johnnysc.holybibleapp.data.books.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val service: BooksService,
        private val gson: Gson,
    ) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(
                service.fetchBooks().string(),
                object : TypeToken<List<BookCloud>>() {}.type
            )
    }
}