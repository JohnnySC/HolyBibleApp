package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [ChaptersCloudMapper.Base]
 *
 * @author Asatryan on 01.08.2021
 */
class ChaptersCloudMapperTest {

    @Test
    fun test_mapping() {
        val mapper = ChaptersCloudMapper.Base(TestMapper(5))
        val actual = mapper.map(
            Pair(
                listOf(
                    ChapterCloud.Base(1),
                    ChapterCloud.Base(2),
                    ChapterCloud.Base(3)
                ),
                listOf(5001, 5002)
            )
        )
        val expected = listOf(
            ChapterData.Base(ChapterId.Base(5, 1), true),
            ChapterData.Base(ChapterId.Base(5, 2), true),
            ChapterData.Base(ChapterId.Base(5, 3), false)
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper(private val bookId: Int) : ToChapterMapper<ChapterData> {
        override fun map(id: Int, isFavorite: Boolean) =
            ChapterData.Base(ChapterId.Base(bookId, chapterIdReal = id), isFavorite)
    }
}