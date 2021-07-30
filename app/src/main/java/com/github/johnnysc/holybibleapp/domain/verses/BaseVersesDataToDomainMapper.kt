package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.BuildString
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.github.johnnysc.holybibleapp.data.verses.VerseDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper

/**
 * @author Asatryan on 17.07.2021
 **/
class BaseVersesDataToDomainMapper(
    private val mapper: VerseDataToDomainMapper<VerseDomain>
) : VersesDataToDomainMapper<VersesDomain>() {

    override fun map(data: Triple<List<VerseData>, BuildString, Pair<Int, Boolean>>): VersesDomain.Success {
        val list = ArrayList(data.first.map { verseData -> verseData.map(mapper) })
        if (!data.third.second)
            list.add(VerseDomain.Next)
        return VersesDomain.Success(list, data.second, data.third.first)
    }

    override fun map(e: Exception) = VersesDomain.Fail(errorType(e))
}