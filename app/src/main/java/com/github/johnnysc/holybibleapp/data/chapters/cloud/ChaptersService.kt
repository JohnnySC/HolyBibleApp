package com.github.johnnysc.holybibleapp.data.chapters.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersService {

    @GET("books/{id}/chapters")
    suspend fun fetchChapters(
        @Path("id") bookId: Int
    ): ResponseBody
}