package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.presentation.core.Communication
import com.github.johnnysc.holybibleapp.presentation.core.ListMapper
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCommunication : Communication<VersesUi>, ChangeFavorite<Int> {
    fun title(textMapper: TextMapper)
    class Base : Communication.Base.Favorites<VersesUi>(), VersesCommunication {

        override fun title(textMapper: TextMapper) {
            liveData.value?.map(ListMapper.Empty(), textMapper)
        }
    }
}