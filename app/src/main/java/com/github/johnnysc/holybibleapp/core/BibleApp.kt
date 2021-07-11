package com.github.johnnysc.holybibleapp.core

import android.app.Application
import com.github.johnnysc.holybibleapp.data.*
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.cache.RealmProvider
import com.github.johnnysc.holybibleapp.data.net.BooksService
import com.github.johnnysc.holybibleapp.domain.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.github.johnnysc.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.*
import com.google.gson.Gson
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        val service = retrofit.create(BooksService::class.java)

        val gson = Gson()
        val cloudDataSource = BooksCloudDataSource.Base(service, gson)
        val cacheDataSource =
            BooksCacheDataSource.Base(RealmProvider.Base(), BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)
        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )
        val communication = BooksCommunication.Base()
        val resourceProvider = ResourceProvider.Base(this)
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(IdCache.Base(this))
        )
    }
}