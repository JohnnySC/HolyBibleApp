package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.presentation.BookUi
import com.github.johnnysc.holybibleapp.presentation.BooksUi
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

/**
 * Test for [BooksDomain.Success]
 * @author Asatryan on 03.07.2021
 */
class BooksDomainTest {

    @Test
    fun test_map() {

        val bookMapper = object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                return BookUi.Base(id, name)
            }
        }

        val domain = BooksDomain.Success(listOf(
            BookData(1, "genesis", "ot"),
            BookData(66, "Revelation", "nt"),
        ), object : BookDataToDomainMapper {
            override fun map(id: Int, name: String) = BookDomain.Base(id, name)
        })

        val actual = domain.map(object : BooksDomainToUiMapper {
            override fun map(books: List<BookDomain>) = BooksUi.Success(books, bookMapper)
            override fun map(errorType: ErrorType): BooksUi {
                throw IllegalStateException()
            }
        })

        val expected = BooksUi.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "genesis"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(66, "Revelation")
            ), bookMapper
        )

        assertEquals(expected, actual)
    }
}