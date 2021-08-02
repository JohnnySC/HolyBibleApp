package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 01.08.2021
 **/
fun List<Int>.isFavorite(matcher: Matcher<Int>) = find {
    matcher.matches(it)
} != null