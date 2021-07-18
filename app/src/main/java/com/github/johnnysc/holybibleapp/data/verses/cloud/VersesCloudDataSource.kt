package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudDataSource {

    suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud.Base>

    class Base(
        private val service: VersesService,
        private val gson: Gson
    ) : VersesCloudDataSource {

        override suspend fun fetchVerses(bookId: Int, chapterId: Int): List<VerseCloud.Base> =
            gson.fromJson(
                service.fetchVerses(bookId, chapterId).string(),
                object : TypeToken<List<VerseCloud.Base>>() {}.type
            )
    }
}