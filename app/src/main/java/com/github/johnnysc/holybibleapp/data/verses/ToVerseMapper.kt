package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 17.07.2021
 **/
interface ToVerseMapper : Abstract.Mapper {

    fun map(id: Int, verseId: Int, text: String): VerseData

    class Base : ToVerseMapper {
        override fun map(id: Int, verseId: Int, text: String) = VerseData(id, verseId, text)
    }
}