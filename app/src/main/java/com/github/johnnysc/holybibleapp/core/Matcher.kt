package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 04.07.2021
 **/
interface Matcher<T> {

    fun matches(arg: T): Boolean
}