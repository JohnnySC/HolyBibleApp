package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Read

/**
 * @author Asatryan on 11.07.2021
 **/
interface ToChapterMapper<T> {

    fun map(id: Int): T

    abstract class Base(private val bookCache: Read<Int>) :
        ToChapterMapper<ChapterData> {
        override fun map(id: Int): ChapterData {
            val realId = realId()
            return ChapterData.Base(
                ChapterId.Base(
                    bookCache.read(),
                    if (realId) id else 0,
                    if (realId) 0 else id
                )
            )
        }

        protected abstract fun realId(): Boolean
    }

    class Cloud(bookCache: Read<Int>) : ToChapterMapper.Base(bookCache) {
        override fun realId() = true
    }

    class Db(bookCache: Read<Int>) : ToChapterMapper.Base(bookCache) {
        override fun realId() = false
    }
}