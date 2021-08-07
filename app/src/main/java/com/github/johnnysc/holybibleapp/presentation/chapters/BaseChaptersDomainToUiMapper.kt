package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.books.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomain
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.books.BookUi
import com.github.johnnysc.holybibleapp.presentation.core.BaseDomainToUiMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    private val bookMapper: BookDomainToUiMapper<BookUi>,
    resourceProvider: ResourceProvider
) : BaseDomainToUiMapper<Pair<List<ChapterDomain>, BookDomain>, ChaptersUi>(resourceProvider),
    ChaptersDomainToUiMapper<ChaptersUi> {

    override fun map(data: Pair<List<ChapterDomain>, BookDomain>) = ChaptersUi.Base(
        ArrayList(data.first.map { chapterDomain -> chapterDomain.map(mapper) }),
        data.second.map(bookMapper)
    )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let { error ->
        ChaptersUi.Base(mutableListOf(ChapterUi.Fail(error)), BookUi.Empty)
    }
}