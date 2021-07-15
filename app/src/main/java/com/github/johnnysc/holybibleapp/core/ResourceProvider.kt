package com.github.johnnysc.holybibleapp.core

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes
import java.io.BufferedReader

/**
 * @author Asatryan on 27.06.2021
 **/
interface ResourceProvider : RawResourceReader, PreferencesProvider {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int) = context.getString(id)
        override fun getString(id: Int, vararg args: Any) = context.getString(id, *args)

        override fun readText(id: Int) = context.resources.openRawResource(id).bufferedReader()
            .use(BufferedReader::readText)

        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}