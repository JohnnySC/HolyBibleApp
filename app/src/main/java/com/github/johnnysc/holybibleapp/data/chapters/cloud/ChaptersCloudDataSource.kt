package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RawResourceReader
import com.github.johnnysc.holybibleapp.data.books.cloud.RussianTranslation
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud.Base>

    abstract class Abstract(private val gson: Gson) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud.Base> = gson.fromJson(
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

    class Russian(
        private val resourceReader: RawResourceReader,
        private val gson: Gson
    ) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud.Base> {
            val text = resourceReader.readText(R.raw.synodal)
            val response = gson.fromJson<RussianTranslation>(
                text,
                object : TypeToken<RussianTranslation>() {}.type
            )

            val book = response.contentAsList().find {
                it.matches(bookId)
            }!!
            return (1..book.chaptersSize()).map {
                ChapterCloud.Base(it)
            }
        }
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        gson: Gson
    ) : ChaptersCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString(bookId: Int) =
            rawResourceReader.readText(R.raw.chapters_successful_response)
    }
}