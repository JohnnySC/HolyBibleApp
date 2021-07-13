package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 12.07.2021
 **/
interface Comparing<T : Comparing<T>> {
    fun sameContent(item: T) = false
    fun same(item: T) = false
}