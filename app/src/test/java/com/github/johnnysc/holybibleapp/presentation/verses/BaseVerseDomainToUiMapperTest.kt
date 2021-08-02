package com.github.johnnysc.holybibleapp.presentation.verses

import android.content.SharedPreferences
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BaseVerseDomainToUiMapper]
 *
 * @author Asatryan on 01.08.2021
 */
class BaseVerseDomainToUiMapperTest {

    private val mapper = BaseVerseDomainToUiMapper(TestResourceProvider())

    @Test
    fun test_base() {
        val actual = mapper.map(1, 101, "text", true)
        val expected = VerseUi.Base(1, "101 text", true)
        assertEquals(expected, actual)
    }

    @Test
    fun test_next() {
        val actual = mapper.map(-200, -200, "next chapter text", false)
        val expected = VerseUi.Next("mock")
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun string(id: Int) = "mock"
        override fun string(id: Int, vararg args: Any) = "mock$args"
        override fun readText(id: Int) = ""
        override fun provideSharedPreferences(name: String): SharedPreferences {
            throw IllegalStateException("not used in tests")
        }

        override fun chooseEnglish() = Unit
        override fun chooseRussian() = Unit
    }
}