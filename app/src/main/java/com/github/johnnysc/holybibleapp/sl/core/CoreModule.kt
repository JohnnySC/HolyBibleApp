package com.github.johnnysc.holybibleapp.sl.core

import android.content.Context
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RealmProvider
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.ScrollPositionCache
import com.github.johnnysc.holybibleapp.presentation.books.BookCache
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterCache
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkData
import com.github.johnnysc.holybibleapp.presentation.languages.Language
import com.github.johnnysc.holybibleapp.presentation.main.MainViewModel
import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.holybibleapp.presentation.main.Navigator

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @author Asatryan on 15.07.2021
 **/
class CoreModule(private val useMocks: Boolean) : BaseModule<MainViewModel> {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var scrollPositionCache: ScrollPositionCache
    lateinit var resourceProvider: ResourceProvider
    lateinit var gson: Gson
    lateinit var realmProvider: RealmProvider
    lateinit var navigator: Navigator
    lateinit var navigationCommunication: NavigationCommunication
    lateinit var bookCache: BookCache
    lateinit var chapterCache: ChapterCache
    lateinit var language: Language
    lateinit var deeplinkData: DeeplinkData
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        gson = Gson()
        resourceProvider = ResourceProvider.Base(context)

        language = if (useMocks)
            Language.Mock(resourceProvider)
        else
            Language.Base(resourceProvider)

        if (language.isChosenRussian())
            resourceProvider.chooseRussian()
        else if (language.isChosenEnglish())
            resourceProvider.chooseEnglish()

        realmProvider = if (useMocks)
            RealmProvider.Mock(context, language)
        else
            RealmProvider.Base(context, language)
        bookCache = BookCache.Base(resourceProvider)
        chapterCache = ChapterCache.Base(resourceProvider)
        navigator = if (useMocks)
            Navigator.Mock(resourceProvider)
        else
            Navigator.Base(resourceProvider)
        navigationCommunication = NavigationCommunication.Base()

        scrollPositionCache = if (useMocks)
            ScrollPositionCache.Mock(resourceProvider)
        else
            ScrollPositionCache.Base(resourceProvider)

        deeplinkData = DeeplinkData.Base(resourceProvider.string(R.string.deeplink))
    }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)

    override fun viewModel() = MainViewModel(navigator, navigationCommunication)
}