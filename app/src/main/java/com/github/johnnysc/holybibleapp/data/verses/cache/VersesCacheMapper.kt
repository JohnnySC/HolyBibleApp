package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCacheMapper : Abstract.Mapper.Data<List<VerseDb>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCacheMapper {
        override fun map(data: List<VerseDb>) = data.map { verseDb ->
            verseDb.map(mapper)
        }
    }
}