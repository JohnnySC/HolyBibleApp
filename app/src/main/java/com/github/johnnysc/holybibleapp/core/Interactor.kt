package com.github.johnnysc.holybibleapp.core

import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 01.08.2021
 **/
interface Interactor : ScrollPositionDomain, ChangeFavorite<Int> {

    abstract class Abstract(
        private val repository: ChangeFavorite<Int>,
        private val scrollPositionCache: ScrollPositionCache
    ) : Interactor {

        override fun changeFavorite(id: Int) = repository.changeFavorite(id)
        override fun scrollPosition(feature: Feature) = scrollPositionCache.scrollPosition(feature)
        override fun saveScrollPosition(feature: Feature, position: Int) =
            scrollPositionCache.saveScrollPosition(feature, position)
    }
}

interface ScrollPositionDomain {
    fun saveScrollPosition(feature: Feature, position: Int)
    fun scrollPosition(feature: Feature): Int
}