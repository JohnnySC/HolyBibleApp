package com.github.johnnysc.holybibleapp.core

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Asatryan on 27.06.2021
 **/
interface ResourceProvider {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int) = context.getString(id)
        override fun getString(id: Int, vararg args: Any) = context.getString(id, *args)
    }
}