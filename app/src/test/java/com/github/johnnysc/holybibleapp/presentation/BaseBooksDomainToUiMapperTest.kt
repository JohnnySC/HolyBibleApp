package com.github.johnnysc.holybibleapp.presentation

import android.content.SharedPreferences
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.domain.books.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.books.BaseBooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.presentation.books.BookUi
import com.github.johnnysc.holybibleapp.presentation.books.BooksUi
import com.github.johnnysc.holybibleapp.presentation.books.UiDataCache
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BaseBooksDomainToUiMapper]
 *
 * @author Asatryan on 04.07.2021
 */
class BaseBooksDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper =
            BaseBooksDomainToUiMapper(resourceProvider, object : BookDomainToUiMapper<BookUi> {
                override fun map(id: Int, name: String, isFavorite: Boolean): BookUi {
                    throw IllegalStateException("not used here")
                }
            }, object : UiDataCache {
                override fun cache(list: List<BookUi>) = ArrayList(list)
                override fun changeState(item: BookUi) = ArrayList<BookUi>()
                override fun saveState() = Unit
                override fun changeFavorite(id: Int) = Unit
            })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(mutableListOf(BookUi.Fail("noConnection")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(mutableListOf(BookUi.Fail("serviceUnavailable")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(mutableListOf(BookUi.Fail("somethingWentWrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun string(id: Int) = when (id) {
            R.string.no_connection_message -> "noConnection"
            R.string.service_unavailable_message -> "serviceUnavailable"
            else -> "somethingWentWrong"
        }

        override fun string(id: Int, vararg args: Any): String {
            return string(id)
        }

        override fun readText(id: Int) = "not used here"

        override fun provideSharedPreferences(name: String): SharedPreferences {
            throw IllegalStateException("not used here")
        }

        override fun chooseEnglish() = Unit
        override fun chooseRussian() = Unit
    }
}