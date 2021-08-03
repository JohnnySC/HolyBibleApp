package com.github.johnnysc.holybibleapp.presentation.verses

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseUi {

    fun <T> map(mapper: VerseUiMapper<T>): T = mapper.map(0, "", false)

    data class Base(
        private val id: Int, private val text: String, private val isFavorite: Boolean
    ) : VerseUi {
        override fun <T> map(mapper: VerseUiMapper<T>) = mapper.map(id, text, isFavorite)
    }

    class Fail(private val text: String) : VerseUi {
        override fun <T> map(mapper: VerseUiMapper<T>) = mapper.map(-1, text, false)
    }

    data class Next(private val text: String) : VerseUi {
        override fun <T> map(mapper: VerseUiMapper<T>) = mapper.map(-2, text, false)
    }

    object Progress : VerseUi
    object Empty : VerseUi
}