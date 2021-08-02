package com.github.johnnysc.holybibleapp.data.verses

/**
 * @author Asatryan on 17.07.2021
 **/
interface ToVerseMapper<T> {

    fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean): T

    class Base : ToVerseMapper<VerseData> {
        override fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean) =
            VerseData.Base(id, verseId, text, isFavorite)
    }
}