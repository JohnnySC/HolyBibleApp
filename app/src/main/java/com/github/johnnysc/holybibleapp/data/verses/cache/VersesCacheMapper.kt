package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.core.FavoritesList
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesCacheMapper :
    Abstract.Mapper.Data<Pair<List<VerseDb>, FavoritesList>, List<VerseData>> {

    class Base(private val mapper: ToVerseMapper<VerseData>) : VersesCacheMapper {
        override fun map(data: Pair<List<VerseDb>, FavoritesList>) = data.let { (verses, ids) ->
            verses.map { verseDb -> verseDb.map(mapper, ids.isFavorite(verseDb)) }
        }
    }
}