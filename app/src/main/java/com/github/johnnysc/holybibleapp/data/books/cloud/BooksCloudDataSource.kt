package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RawResourceReader
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksCloudDataSource {

    suspend fun fetchBooks(): List<BookCloud>

    abstract class Abstract(private val gson: Gson) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud.Base> = gson.fromJson(
            getDataAsString(),
            object : TypeToken<List<BookCloud.Base>>() {}.type
        )

        protected abstract suspend fun getDataAsString(): String
    }

    class Base(
        private val languages: ChosenLanguage,
        private val englishCloudDataSource: BooksCloudDataSource,
        private val russianDataSource: BooksCloudDataSource
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks() = (
                if (languages.isChosenRussian())
                    russianDataSource
                else
                    englishCloudDataSource
                ).fetchBooks()
    }

    class Russian(
        private val resourceReader: RawResourceReader,
        private val gson: Gson
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            val text = resourceReader.readText(R.raw.synodal)
            val translation = gson.fromJson<RussianTranslation>(
                text,
                object : TypeToken<RussianTranslation>() {}.type
            )
            return translation.contentAsList()
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    class English(
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