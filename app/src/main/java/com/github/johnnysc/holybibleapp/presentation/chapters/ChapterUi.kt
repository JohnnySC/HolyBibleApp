package com.github.johnnysc.holybibleapp.presentation.chapters

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterUi {

    fun <T> map(mapper: ChapterUiMapper<T>): T = mapper.map(0, 0, "", false)

    class Base(
        private val visibleId: Int,
        private val id: Int,
        private val text: String,
        private val isFavorite: Boolean = false,
    ) : ChapterUi {

        override fun <T> map(mapper: ChapterUiMapper<T>) =
            mapper.map(visibleId, id, text, isFavorite)
    }

    class Fail(private val message: String) : ChapterUi {
        override fun <T> map(mapper: ChapterUiMapper<T>) =
            mapper.map(-1, -1, message, false)
    }

    object Progress : ChapterUi
    object Empty : ChapterUi
}