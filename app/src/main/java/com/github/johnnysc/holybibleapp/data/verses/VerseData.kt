package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDataToDbMapper
import com.github.johnnysc.holybibleapp.data.verses.cache.VerseDb
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain

/**
 * @author Asatryan on 17.07.2021
 **/
class VerseData(
    private val id: Int,
    private val verseId: Int,
    private val text: String
) : Abstract.Object.ToDb<VerseDb, VerseDataToDbMapper>,
    Abstract.Object<VerseDomain, VerseDataToDomainMapper> {
    override fun map(mapper: VerseDataToDomainMapper) = mapper.map(verseId, text)
    override fun mapBy(mapper: VerseDataToDbMapper, db: DbWrapper<VerseDb>) =
        mapper.mapToDb(id, verseId, text, db)
}