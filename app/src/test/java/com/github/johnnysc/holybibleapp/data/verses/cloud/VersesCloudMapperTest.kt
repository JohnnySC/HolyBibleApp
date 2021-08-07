package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.data.core.FavoritesList
import com.github.johnnysc.holybibleapp.data.verses.ToVerseMapper
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [VersesCloudMapper.Base]
 *
 * @author Asatryan on 01.08.2021
 */
class VersesCloudMapperTest {

    @Test
    fun test_mapping() {
        val mapper = VersesCloudMapper.Base(TestMapper())
        val actual = mapper.map(
            Pair(
                listOf(
                    VerseCloud.Base(5_004_001, 1, "one"),
                    VerseCloud.Base(5_004_002, 2, "two"),
                    VerseCloud.Base(5_004_003, 3, "three"),
                ),
                FavoritesList(listOf(5_004_001, 5_004_00_2))
            )
        )
        val expected = listOf(
            VerseData.Base(5_004_001, 1, "one", true),
            VerseData.Base(5_004_00_2, 2, "two", true),
            VerseData.Base(5_004_003, 3, "three", false),
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper : ToVerseMapper<VerseData> {
        override fun map(id: Int, verseId: Int, text: String, isFavorite: Boolean) =
            VerseData.Base(id, verseId, text, isFavorite)
    }
}