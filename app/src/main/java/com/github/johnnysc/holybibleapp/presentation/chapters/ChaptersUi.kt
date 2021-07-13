package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersUi : Abstract.Object<Unit, ChaptersCommunication> {

    class Base(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }
}