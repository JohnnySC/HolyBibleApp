package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.domain.books.BookDataMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.core.BaseDataToDomainMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper<ChapterDomain>,
    private val bookMapper: BookDataMapper<BookDomain>
) : BaseDataToDomainMapper<Pair<List<ChapterData>, BookData>, ChaptersDomain>(),
    ChaptersDataToDomainMapper<ChaptersDomain> {

    override fun map(data: Pair<List<ChapterData>, BookData>) = data.let { (chapters, book) ->
        ChaptersDomain.Success(
            chapters.map { chapter -> chapter.map(mapper) },
            book.map(bookMapper)
        )
    }

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}