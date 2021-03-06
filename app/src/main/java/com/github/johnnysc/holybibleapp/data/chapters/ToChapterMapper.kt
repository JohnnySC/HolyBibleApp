package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.Read

/**
 * @author Asatryan on 11.07.2021
 **/
interface ToChapterMapper<T> {

    fun map(id: Int, isFavorite: Boolean = false): T

    abstract class Base(private val bookCache: Read<Int>, private val multiply: Multiply) :
        ToChapterMapper<ChapterData> {
        override fun map(id: Int, isFavorite: Boolean) = realId().let { realId ->
            ChapterData.Base(
                ChapterId.Base(
                    bookCache.read(),
                    if (realId) id else 0,
                    if (realId) 0 else id,
                    multiply),
                isFavorite
            )
        }

        protected abstract fun realId(): Boolean
    }

    class Cloud(bookCache: Read<Int>, multiply: Multiply) :
        ToChapterMapper.Base(bookCache, multiply) {
        override fun realId() = true
    }

    class Db(bookCache: Read<Int>, multiply: Multiply) : ToChapterMapper.Base(bookCache, multiply) {
        override fun realId() = false
    }
}