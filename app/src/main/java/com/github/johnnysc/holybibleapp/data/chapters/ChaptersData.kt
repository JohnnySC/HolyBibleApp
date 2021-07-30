package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.core.TextMapper

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersData : Abstract.DataObject, Matcher<Int> {

    abstract fun <T> map(
        mapper: ChaptersDataToDomainMapper<T>,
        book: Abstract.Object<Unit, TextMapper>
    ): T

    override fun matches(arg: Int) = false

    data class Success(
        private val chapters: List<ChapterData>
    ) : ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(Pair(chapters, book))

        override fun matches(arg: Int) = arg == chapters.size
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(
            mapper: ChaptersDataToDomainMapper<T>,
            book: Abstract.Object<Unit, TextMapper>
        ) = mapper.map(e)
    }
}