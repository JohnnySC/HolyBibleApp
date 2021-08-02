package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 01.08.2021
 */
interface Favorites {
    fun favorites(limits: Limits): List<Int>
}