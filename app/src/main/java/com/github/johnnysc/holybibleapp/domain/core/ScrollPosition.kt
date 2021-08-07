package com.github.johnnysc.holybibleapp.domain.core

import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 01.08.2021
 **/
interface ScrollPosition {
    fun saveScrollPosition(feature: Feature, position: Int)
    fun scrollPosition(feature: Feature): Int
}