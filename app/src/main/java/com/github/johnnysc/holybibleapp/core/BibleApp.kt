package com.github.johnnysc.holybibleapp.core

import android.app.Application
import com.github.johnnysc.holybibleapp.presentation.NavigationCommunication
import com.github.johnnysc.holybibleapp.presentation.MainViewModel
import com.github.johnnysc.holybibleapp.presentation.Navigator
import com.github.johnnysc.holybibleapp.data.books.*
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksService
import com.github.johnnysc.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChaptersCacheMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudDataSource
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersCloudMapper
import com.github.johnnysc.holybibleapp.data.chapters.cloud.ChaptersService
import com.github.johnnysc.holybibleapp.domain.books.BaseBookDataToDomainMapper
import retrofit2.Retrofit
import com.github.johnnysc.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import com.github.johnnysc.holybibleapp.domain.chapters.*
import com.github.johnnysc.holybibleapp.presentation.books.*
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.BaseChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersCommunication
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersViewModel
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
    lateinit var booksViewModel: BooksViewModel
    lateinit var chaptersViewModel: ChaptersViewModel

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
        val realmProvider = RealmProvider.Base()
        val cacheDataSource =
            BooksCacheDataSource.Base(realmProvider, BookDataToDbMapper.Base())
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
        val bookCache = BookCache.Base(this)
        val chaptersRepository = ChaptersRepository.Base(
            ChaptersCloudDataSource.Base(
                retrofit.create(ChaptersService::class.java),
                gson
            ),
            ChaptersCacheDataSource.Base(realmProvider, ChapterDataToDbMapper.Base()),
            ChaptersCloudMapper.Base(ToChapterMapper.Cloud(bookCache)),
            ChaptersCacheMapper.Base(ToChapterMapper.Db(bookCache)),
            bookCache
        )
        val chaptersInteractor = ChaptersInteractor.Base(
            chaptersRepository,
            BaseChaptersDataToDomainMapper(BaseChapterDataToDomainMapper())
        )

        val navigator = Navigator.Base(this)
        val navigationCommunication = NavigationCommunication.Base()
        mainViewModel = MainViewModel(navigator, navigationCommunication)

        booksViewModel = BooksViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(CollapsedIdsCache.Base(this)),
            bookCache,
            navigator,
            navigationCommunication
        )

        chaptersViewModel = ChaptersViewModel(
            chaptersInteractor,
            ChaptersCommunication.Base(),
            BaseChaptersDomainToUiMapper(
                BaseChapterDomainToUiMapper(ChapterIdToUiMapper.Base(resourceProvider)),
                resourceProvider
            ),
            navigator,
            bookCache
        ) //todo IoC
    }
}