package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BaseBookDomainToUiMapper]
 *
 * @author Asatryan on 06.08.2021
 */
class BaseBookDomainToUiMapperTest {

    @Test
    fun test_old_testament() {
        val mapper = BaseBookDomainToUiMapper(TestResourceProvider())
        val actual = mapper.map(Int.MIN_VALUE, "old")
        val expected = BookUi.Testament(Int.MIN_VALUE, "ot")
        assertEquals(expected, actual)
    }

    @Test
    fun test_new_testament() {
        val mapper = BaseBookDomainToUiMapper(TestResourceProvider())
        val actual = mapper.map(Int.MAX_VALUE, "new")
        val expected = BookUi.Testament(Int.MAX_VALUE, "nt")
        assertEquals(expected, actual)
    }

    @Test
    fun test_book() {
        val mapper = BaseBookDomainToUiMapper(TestResourceProvider())
        val actual = mapper.map(5, "bookName")
        val expected = BookUi.Base(5, "bookName")
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun string(id: Int) = if (id == R.string.new_testament) "nt" else "ot"
        override fun string(id: Int, vararg args: Any) = "mocked$args"
        override fun readText(id: Int) = ""
        override fun provideSharedPreferences(name: String) =
            throw IllegalStateException("not used here")

        override fun chooseEnglish() = Unit
        override fun chooseRussian() = Unit

    }
}