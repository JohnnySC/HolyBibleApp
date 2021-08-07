package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.presentation.core.SaveScreen
import com.github.johnnysc.holybibleapp.presentation.main.NavigateBack
import com.github.johnnysc.holybibleapp.presentation.main.NavigateForward

/**
 * @author Asatryan on 18.07.2021
 **/
interface LanguagesNavigator : NavigateForward, Read<Int>, SaveScreen, NavigateBack {

    fun nextFromLanguages(): Int
}