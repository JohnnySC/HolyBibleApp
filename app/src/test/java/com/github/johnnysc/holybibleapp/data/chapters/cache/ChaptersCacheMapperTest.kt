package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [ChaptersCacheMapper.Base]
 *
 * @author Asatryan on 02.08.2021
 */
class ChaptersCacheMapperTest {

    @Test
    fun test_mapping() {
        val mapper = ChaptersCacheMapper.Base(TestMapper(7))
        val actual = mapper.map(
            Pair(
                listOf(
                    ChapterDb().apply {
                        id = 7001
                    },
                    ChapterDb().apply {
                        id = 7002
                    },
                    ChapterDb().apply {
                        id = 7003
                    },
                ),
                listOf(7001, 7002)
            )
        )
        val expected = listOf(
            ChapterData.Base(ChapterId.Base(7, 1), true),
            ChapterData.Base(ChapterId.Base(7, 2), true),
            ChapterData.Base(ChapterId.Base(7, 3), false),
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper(private val bookId: Int) : ToChapterMapper<ChapterData> {
        override fun map(id: Int, isFavorite: Boolean) =
            ChapterData.Base(ChapterId.Base(bookId, chapterIdGenerated = id), isFavorite)
    }
}