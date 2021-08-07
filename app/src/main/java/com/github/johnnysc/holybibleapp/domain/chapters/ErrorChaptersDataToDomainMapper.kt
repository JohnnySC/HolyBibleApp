package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.core.BaseDataToDomainMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class ErrorChaptersDataToDomainMapper :
    BaseDataToDomainMapper<Pair<List<ChapterData>, BookData>, ErrorType>(),
    ChaptersDataToDomainMapper<ErrorType> {
    override fun map(data: Pair<List<ChapterData>, BookData>) = ErrorType.GENERIC_ERROR
    override fun map(e: Exception) = errorType(e)
}