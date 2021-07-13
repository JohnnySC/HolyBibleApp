package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudDataSource {

    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    class Base(
        private val service: ChaptersService,
        private val gson: Gson
    ) : ChaptersCloudDataSource {

        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> =
            gson.fromJson(
                service.fetchChapters(bookId).string(),
                object : TypeToken<List<ChapterCloud>>() {}.type //todo make a class (DRY)
            )
    }
}