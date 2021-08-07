package com.github.johnnysc.holybibleapp.data.core

/**
 * @author Asatryan on 17.07.2021
 **/
interface Limits : Min, Max

interface Min {
    fun min(): Int
}

interface Max {
    fun max(): Int
}