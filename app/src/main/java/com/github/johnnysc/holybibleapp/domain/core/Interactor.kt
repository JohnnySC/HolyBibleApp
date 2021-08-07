package com.github.johnnysc.holybibleapp.domain.core

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 01.08.2021
 **/
interface Interactor : ScrollPosition, ChangeFavorite<Int> {

    abstract class Abstract(
        private val repository: ChangeFavorite<Int>,
        private val scrollPositionCache: ScrollPosition
    ) : Interactor {

        override fun changeFavorite(id: Int) = repository.changeFavorite(id)
        override fun scrollPosition(feature: Feature) = scrollPositionCache.scrollPosition(feature)
        override fun saveScrollPosition(feature: Feature, position: Int) =
            scrollPositionCache.saveScrollPosition(feature, position)
    }
}