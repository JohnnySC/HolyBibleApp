package com.github.johnnysc.holybibleapp.presentation.main

import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 13.07.2021
 **/
interface NavigationCommunication : Communication<Int> {
    class Base : Communication.Base<Int>(), NavigationCommunication
}