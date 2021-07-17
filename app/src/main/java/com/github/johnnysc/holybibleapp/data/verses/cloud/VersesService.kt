package com.github.johnnysc.holybibleapp.data.verses.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesService {

    @GET("books/{bookId}/chapters/{chapterId}")
    suspend fun fetchVerses(
        @Path("bookId") bookId: Int,
        @Path("chapterId") chapterId: Int,
    ): ResponseBody
}