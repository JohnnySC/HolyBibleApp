package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.net.BookCloud
import com.github.johnnysc.holybibleapp.data.net.BooksService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val service: BooksService,
        private val gson: Gson
    ) : BooksCloudDataSource {
        private val type = object : TypeToken<List<BookCloud>>() {}.type //todo make a wrapper

        override suspend fun fetchBooks(): List<BookCloud> =
            gson.fromJson(service.fetchBooks().string(), type)
    }
}