package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudMapper : Abstract.Mapper.Data<List<VerseCloud>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCloudMapper {
        override fun map(data: List<VerseCloud>): List<VerseData> = data.map { verse ->
            verse.map(mapper)
        }
    }
}