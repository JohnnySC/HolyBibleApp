package com.github.johnnysc.holybibleapp.presentation.main

import com.github.johnnysc.holybibleapp.core.Read

/**
 * @author Asatryan on 15.07.2021
 **/
interface MainNavigator : Read<Int>, NavigateBack {

    fun fragment(id: Int): BaseFragment<*>
    fun showLanguagesFragment(communication: NavigationCommunication)
}

interface NavigateBack {
    fun canGoBack(): Boolean
    fun navigateBack(navigationCommunication: NavigationCommunication)
}