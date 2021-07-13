package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 11.07.2021
 **/
interface Save<T> {
    fun save(data: T)
}