package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Multiply

/**
 * @author Asatryan on 27.07.2021
 **/
interface VerseToWrapperMapper : Abstract.Mapper {

    fun map(text: String): VerseCloud

    class Base(
        private val bookId: Int,
        private val chapterId: Int,
        private val id: Int
    ) : VerseToWrapperMapper {
        override fun map(text: String): VerseCloud {
            val finalId = Multiply(2).map(bookId) + Multiply(1).map(chapterId) + id
            return VerseRuWrapper(finalId, id, text)
        }
    }
}