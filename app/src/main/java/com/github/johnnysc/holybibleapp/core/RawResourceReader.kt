package com.github.johnnysc.holybibleapp.core

import androidx.annotation.RawRes

/**
 * @author Asatryan on 15.07.2021
 **/
interface RawResourceReader {

    fun readText(@RawRes id: Int): String
}