package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesRepository

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesInteractor {

    suspend fun fetchVerses(): VersesDomain

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>
    ) : VersesInteractor {

        override suspend fun fetchVerses() = repository.fetchData().map(mapper)
    }
}