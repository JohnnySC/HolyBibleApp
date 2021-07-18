package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersDomain {

    abstract fun <T> map(mapper: ChaptersDomainToUiMapper<T>): T

    data class Success(
        private val chapters: List<ChapterDomain>
    ) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) = mapper.map(chapters)
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) = mapper.map(errorType)
    }
}