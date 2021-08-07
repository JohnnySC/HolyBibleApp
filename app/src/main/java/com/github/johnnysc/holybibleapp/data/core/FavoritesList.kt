package com.github.johnnysc.holybibleapp.data.core

import com.github.johnnysc.holybibleapp.core.Matcher

/**
 * @author Asatryan on 01.08.2021
 **/
class FavoritesList(private val ids: List<Int>) {
    fun isFavorite(matcher: Matcher<Int>, transform: (Int) -> Int) =
        findFavorite(ids.map(transform), matcher)

    fun isFavorite(matcher: Matcher<Int>) = findFavorite(ids, matcher)

    private fun findFavorite(list: List<Int>, matcher: Matcher<Int>) =
        list.find { matcher.matches(it) } != null
}