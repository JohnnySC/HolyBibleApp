package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.presentation.core.Communication

/**
 * @author Asatryan on 12.07.2021
 **/
interface ChaptersCommunication : Communication<ChaptersUi>, ChangeFavorite<Int> {
    class Base : Communication.Base.Favorites<ChaptersUi>(), ChaptersCommunication
}