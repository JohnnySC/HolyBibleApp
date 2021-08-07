package com.github.johnnysc.holybibleapp.presentation.deeplink

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.verses.VerseDomain
import com.github.johnnysc.holybibleapp.presentation.verses.BaseVerseDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUi
import com.github.johnnysc.holybibleapp.presentation.verses.VersesUi
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [DeeplinkVersesDomainToUiMapper]
 *
 * @author Asatryan on 05.08.2021
 */
class DeeplinkVersesDomainToUiMapperTest {

    @Test
    fun test_found() {
        val resourceProvider = TestResourceProvider()
        val mapper = DeeplinkVersesDomainToUiMapper(
            TestContainer(3),
            BaseVerseDomainToUiMapper(resourceProvider),
            resourceProvider
        )
        val source = listOf(
            VerseDomain.Base(101, 1, "one", false),
            VerseDomain.Base(102, 2, "two", false),
            VerseDomain.Base(103, 3, "three", false),
        )
        val actual = mapper.map(Triple(source, BookDomain.Base(0, ""), 7))
        val expected = VersesUi.Base(mutableListOf(VerseUi.Base(103, "3 three", false)), "mockWithArgs")
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_found() {
        val resourceProvider = TestResourceProvider()
        val mapper = DeeplinkVersesDomainToUiMapper(
            TestContainer(4),
            BaseVerseDomainToUiMapper(resourceProvider),
            resourceProvider
        )
        val source = listOf(
            VerseDomain.Base(101, 1, "one", false),
            VerseDomain.Base(102, 2, "two", false),
            VerseDomain.Base(103, 3, "three", false),
        )
        val actual = mapper.map(Triple(source, BookDomain.Base(0, ""), 7))
        val expected = VersesUi.Base(mutableListOf(), "mockWithArgs")
        assertEquals(expected, actual)
    }

    private inner class TestContainer(private val id: Int) : Read<Int> {
        override fun read() = id
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun string(id: Int) = "mock"
        override fun string(id: Int, vararg args: Any) = "mockWithArgs"
        override fun readText(id: Int) = ""
        override fun provideSharedPreferences(name: String) =
            throw IllegalStateException("not used here")

        override fun chooseEnglish() = Unit
        override fun chooseRussian() = Unit
    }
}