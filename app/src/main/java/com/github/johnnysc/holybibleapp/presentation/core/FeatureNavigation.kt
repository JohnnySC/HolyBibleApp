package com.github.johnnysc.holybibleapp.presentation.core

import com.github.johnnysc.holybibleapp.presentation.main.NavigationCommunication
import com.github.johnnysc.holybibleapp.presentation.main.Navigator
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 06.08.2021
 **/
interface FeatureNavigation {
    fun init()
    fun showNextScreen()

    class Base(
        private val navigator: Navigator,
        private val navigationCommunication: NavigationCommunication,
        private val feature: Feature,
    ) : FeatureNavigation {
        override fun init() = navigator.save(feature)
        override fun showNextScreen() = navigator.nextScreen(navigationCommunication)
    }
}