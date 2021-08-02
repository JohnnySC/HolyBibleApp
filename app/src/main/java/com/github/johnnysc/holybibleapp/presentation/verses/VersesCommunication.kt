package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.Communication

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCommunication : Communication<VersesUi>, ChangeFavorite<Int> {
    class Base : Communication.Base.Favorites<VersesUi>(), VersesCommunication
}