package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RawResourceReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    abstract class Abstract(private val gson: Gson) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> = gson.fromJson(
            getDataAsString(),
            object : TypeToken<List<BookCloud>>() {}.type
        )

        protected abstract suspend fun getDataAsString(): String
    }

    class Base(
        private val service: BooksService,
        gson: Gson,
    ) : BooksCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString() = service.fetchBooks().string()
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        gson: Gson
    ) : BooksCloudDataSource.Abstract(gson) {

        override suspend fun getDataAsString() =
            rawResourceReader.readText(R.raw.books_successful_response)
    }
}