package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.github.johnnysc.holybibleapp.data.verses.VerseDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>
) : VersesDataToDomainMapper<VersesDomain>() {

    override fun map(data: List<VerseData>) = VersesDomain.Success(data.map { verseData ->
        verseData.map(mapper)
    })

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}