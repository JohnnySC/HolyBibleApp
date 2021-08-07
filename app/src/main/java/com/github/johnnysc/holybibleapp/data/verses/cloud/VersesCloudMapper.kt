package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.core.FavoritesList
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCloudMapper :
    Abstract.Mapper.Data<Pair<List<VerseCloud>, FavoritesList>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCloudMapper {
        override fun map(data: Pair<List<VerseCloud>, FavoritesList>) = data.let { (verses, ids) ->
            verses.map { verse -> verse.map(mapper, ids.isFavorite(verse)) }
        }
    }
}