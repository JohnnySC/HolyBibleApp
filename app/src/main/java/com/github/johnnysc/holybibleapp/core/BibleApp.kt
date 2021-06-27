package com.github.johnnysc.holybibleapp.core

import android.app.Application
import com.github.johnnysc.holybibleapp.data.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.BooksRepository
import com.github.johnnysc.holybibleapp.data.cache.BookCacheMapper
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.cache.RealmProvider
import com.github.johnnysc.holybibleapp.data.net.BookCloudMapper
import com.github.johnnysc.holybibleapp.data.net.BooksService
import retrofit2.Retrofit
import com.github.johnnysc.holybibleapp.domain.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.BooksInteractor

/**
 * @author Asatryan on 26.06.2021
 **/
class BibleApp : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //todo log http calls
            .build()
        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
    }
}