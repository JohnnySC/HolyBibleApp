package com.github.johnnysc.holybibleapp.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import androidx.annotation.StringRes
import com.github.johnnysc.holybibleapp.presentation.languages.ChooseLanguages
import java.io.BufferedReader
import java.util.*

/**
 * @author Asatryan on 27.06.2021
 **/
interface ResourceProvider : RawResourceReader, PreferencesProvider, ChooseLanguages {

    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: Any): String

    class Base(private var context: Context) : ResourceProvider {
        override fun string(id: Int) = context.getString(id)
        override fun string(id: Int, vararg args: Any) = context.getString(id, *args)

        override fun readText(id: Int) = context.resources.openRawResource(id).bufferedReader()
            .use(BufferedReader::readText)

        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

        override fun chooseEnglish() = changeLanguage("en", Locale.ENGLISH)
        override fun chooseRussian() = changeLanguage("ru", Locale("ru", "RU"))

        private fun changeLanguage(lang: String, locale: Locale) {
            val conf = context.resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                conf.setLocales(LocaleList.forLanguageTags(lang))
            else
                conf.setLocale(locale)
            context = context.createConfigurationContext(conf)
        }
    }
}