package com.github.johnnysc.holybibleapp.core

import androidx.annotation.StringRes

/**
 * @author Asatryan on 27.07.2021
 **/
interface BuildString {
    fun build(resourceProvider: ResourceProvider, @StringRes id: Int, arg: Any): String

    class Empty : BuildString {
        override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any) = ""
    }
}