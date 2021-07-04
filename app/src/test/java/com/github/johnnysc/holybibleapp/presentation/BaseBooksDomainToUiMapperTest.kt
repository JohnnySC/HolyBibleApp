package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.domain.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.ErrorType
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

/**
 * Test for [BaseBooksDomainToUiMapper]
 * @author Asatryan on 04.07.2021
 */
class BaseBooksDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                throw IllegalStateException("not used here")
            }
        })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(listOf(BookUi.Fail("noConnection")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(listOf(BookUi.Fail("serviceUnavailable")))
        assertEquals(expected, actual)
        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(listOf(BookUi.Fail("somethingWentWrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "noConnection"
            R.string.service_unavailable_message -> "serviceUnavailable"
            else -> "somethingWentWrong"
        }
    }
}