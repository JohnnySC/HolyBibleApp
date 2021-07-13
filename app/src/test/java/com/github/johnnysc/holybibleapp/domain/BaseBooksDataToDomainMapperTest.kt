package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.*
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException
import java.net.UnknownHostException

/**
 * Test for [BaseBooksDataToDomainMapper]
 *
 * @author Asatryan on 04.07.2021
 */
class BaseBooksDataToDomainMapperTest {

    private val mapper = BaseBooksDataToDomainMapper(object : BookDataToDomainMapper {
        override fun map(id: Int, name: String) = BookDomain.Base(id, name)
    })

    @Test
    fun test_success() {
        val actual = mapper.map(
            listOf(
                BookData(1, "genesis", "ot"),
                BookData(66, "Revelation", "nt")
            )
        )

        val data = mutableListOf(
            BookDomain.Testament(TestamentType.OLD),
            BookDomain.Base(1, "genesis"),
            BookDomain.Testament(TestamentType.NEW),
            BookDomain.Base(66, "Revelation")
        )

        val expected = BooksDomain.Success(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var actual = mapper.map(UnknownHostException())
        var expected = BooksDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalStateException())
        expected = BooksDomain.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }
}