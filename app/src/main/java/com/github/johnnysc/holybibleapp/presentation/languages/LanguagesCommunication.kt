package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 18.07.2021
 **/
interface LanguagesCommunication : Communication<LanguagesUi> {
    class Base : Communication.Base<LanguagesUi>(), LanguagesCommunication
}