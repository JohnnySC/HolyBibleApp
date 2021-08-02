package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RawResourceReader
import com.github.johnnysc.holybibleapp.data.books.cloud.BookRu
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.IllegalStateException

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    abstract class Abstract(private val gson: Gson) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> = gson.fromJson(
            getDataAsString(bookId),
            object : TypeToken<List<ChapterCloud.Base>>() {}.type
        )

        protected abstract suspend fun getDataAsString(bookId: Int): String
    }

    class Base(
        private val languages: ChosenLanguage,
        private val english: ChaptersCloudDataSource,
        private val russian: ChaptersCloudDataSource
    ) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int) = (
                if (languages.isChosenRussian())
                    russian
                else
                    english
                ).fetchChapters(bookId)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    class English(
        private val service: ChaptersService,
        gson: Gson
    ) : ChaptersCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString(bookId: Int) = service.fetchChapters(bookId).string()
    }

    class Russian(private val booksRu: () -> List<BookRu>) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int) = booksRu().find {
            it.matches(bookId)
        }?.contentAsList() ?: throw IllegalStateException("bookId not found")
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        gson: Gson
    ) : ChaptersCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString(bookId: Int) =
            rawResourceReader.readText(R.raw.chapters_successful_response)
    }
}