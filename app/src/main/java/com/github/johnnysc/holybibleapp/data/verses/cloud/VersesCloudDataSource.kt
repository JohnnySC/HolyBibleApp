package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChapterCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudDataSource {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud>

    class Base(
        private val service: VersesService,
        private val gson: Gson
    ) : VersesCloudDataSource {

        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud> =
            gson.fromJson(
                service.fetchVerses(bookId, chapterId).string(),
                object : TypeToken<List<VerseCloud>>() {}.type
            )
    }
}