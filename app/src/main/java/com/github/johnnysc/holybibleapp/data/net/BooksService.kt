package com.github.johnnysc.holybibleapp.data.net

import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksService {

    @GET("books")
    suspend fun fetchBooks(): ResponseBody
}