package com.github.johnnysc.holybibleapp.data.books.cloud

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.core.FavoritesList
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BooksCloudMapper.Base]
 *
 * @author Asatryan on 01.08.2021
 */
class BooksCloudMapperTest {

    @Test
    fun test_mapping() {
        val mapper = BooksCloudMapper.Base(TestMapper())
        val actual = mapper.map(
            Pair(
                listOf(
                    BookCloud.Base(1, "one", "ot"),
                    BookCloud.Base(2, "two", "ot"),
                    BookCloud.Base(3, "three", "nt"),
                ),
                FavoritesList(listOf(1, 2))
            )
        )
        val expected = listOf(
            BookData.Base(1, "one", "ot", true),
            BookData.Base(2, "two", "ot", true),
            BookData.Base(3, "three", "nt", false)
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            BookData.Base(id, name, testament, isFavorite)
    }
}