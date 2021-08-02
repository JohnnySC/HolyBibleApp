package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.isFavorite
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudMapper :
    Abstract.Mapper.Data<Pair<List<VerseCloud>, List<Int>>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCloudMapper {
        override fun map(data: Pair<List<VerseCloud>, List<Int>>): List<VerseData> =
            data.first.map { verse ->
                verse.map(mapper, data.second.isFavorite(verse))
            }
    }
}