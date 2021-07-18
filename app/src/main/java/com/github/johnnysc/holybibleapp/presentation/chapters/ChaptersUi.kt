package com.github.johnnysc.holybibleapp.presentation.chapters

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersUi  {

    abstract fun map(mapper:ChaptersCommunication)

    class Base(private val chapters: List<ChapterUi>) : ChaptersUi() {
        override fun map(mapper: ChaptersCommunication) = mapper.map(chapters)
    }
}