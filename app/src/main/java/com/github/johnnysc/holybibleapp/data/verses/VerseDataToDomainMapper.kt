package com.github.johnnysc.holybibleapp.data.verses

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDataToDomainMapper<T> {
    fun map(verseId: Int, text: String): T
}