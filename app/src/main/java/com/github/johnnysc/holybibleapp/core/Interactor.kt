package com.github.johnnysc.holybibleapp.core

import com.github.johnnysc.holybibleapp.presentation.main.ScrollPosition
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 01.08.2021
 **/
interface Interactor : ScrollPosition, ChangeFavorite<Int> {

    abstract class Abstract(
        private val repository: ChangeFavorite<Int>,
        private val scrollPositionCache: ScrollPositionCache,
        private val feature: Feature
    ) : Interactor {

        override fun changeFavorite(id: Int) = repository.changeFavorite(id)
        override fun scrollPosition() = scrollPositionCache.scrollPosition(feature)
        override fun saveScrollPosition(position: Int) =
            scrollPositionCache.saveScrollPosition(feature, position)
    }
}