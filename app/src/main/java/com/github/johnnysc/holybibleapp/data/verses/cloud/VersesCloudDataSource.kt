package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RawResourceReader
import com.github.johnnysc.holybibleapp.data.books.cloud.RussianTranslation
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudDataSource {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud>

    abstract class Abstract(private val gson: Gson) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud> =
            gson.fromJson(
                getDataAsString(bookId, chapterId),
                object : TypeToken<List<VerseCloud.Base>>() {}.type
            )

        protected abstract suspend fun getDataAsString(bookId: Int, chapterId: Int): String
    }

    class Base(
        private val language: ChosenLanguage,
        private val english: VersesCloudDataSource,
        private val russian: VersesCloudDataSource
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int) = (
                if (language.isChosenRussian())
                    russian
                else
                    english
                ).fetchVerses(bookId, chapterId)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    class English(
        private val service: VersesService,
        gson: Gson
    ) : VersesCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString(bookId: Int, chapterId: Int) =
            service.fetchVerses(bookId, chapterId).string()
    }

    class Russian(
        private val resourceReader: RawResourceReader,
        private val gson: Gson
    ) : VersesCloudDataSource {
        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud> {
            val text = resourceReader.readText(R.raw.synodal)
            val response = gson.fromJson<RussianTranslation>(
                text,
                object : TypeToken<RussianTranslation>() {}.type
            )

            return response.contentAsList().find {
                it.matches(bookId)
            }!!.contentAsList().find {
                it.matches(chapterId)
            }!!.contentAsList().map { (id, verse) ->
                verse.map(VerseToWrapperMapper.Base(bookId, chapterId, id))
            }
        }
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        gson: Gson
    ) : VersesCloudDataSource.Abstract(gson) {
        override suspend fun getDataAsString(bookId: Int, chapterId: Int) =
            rawResourceReader.readText(R.raw.verses_successful_response)
    }
}