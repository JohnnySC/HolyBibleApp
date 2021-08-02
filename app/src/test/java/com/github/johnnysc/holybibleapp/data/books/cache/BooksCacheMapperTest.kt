package com.github.johnnysc.holybibleapp.data.books.cache

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BooksCacheMapper.Base]
 *
 * @author Asatryan on 02.08.2021
 */
class BooksCacheMapperTest {

    @Test
    fun test_mapping() {
        val mapper = BooksCacheMapper.Base(TestMapper())
        val actual = mapper.map(
            Pair(
                listOf(
                    BookDb().apply {
                        id = 1
                        name = "one"
                        testament = "ot"
                    },
                    BookDb().apply {
                        id = 2
                        name = "two"
                        testament = "ot"
                    },
                    BookDb().apply {
                        id = 3
                        name = "three"
                        testament = "nt"
                    }),
                listOf(1, 2)
            )
        )
        val expected = listOf(
            BookData.Base(1,"one","ot", true),
            BookData.Base(2,"two","ot", true),
            BookData.Base(3,"three","nt", false),
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper : ToBookMapper<BookData> {
        override fun map(id: Int, name: String, testament: String, isFavorite: Boolean) =
            BookData.Base(id, name, testament, isFavorite)

    }
}