package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.BuildString
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BaseVersesDataToDomainMapper]
 *
 * @author Asatryan on 30.07.2021
 */
class BaseVersesDataToDomainMapperTest {

    @Test
    fun test_last_item() {
        val mapper = BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper())
        val buildString = object : BuildString {
            override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any) = "mocked"
        }
        val list = listOf(VerseDomain.Base(1, 1001, "verse1"))
        val expected = VersesDomain.Success(list, buildString, 0)
        val actual = mapper.map(
            Triple(
                listOf(VerseData.Base(1, 1001, "verse1")),
                buildString,
                Pair(0, true)
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_last_item() {
        val mapper = BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper())
        val buildString = object : BuildString {
            override fun build(resourceProvider: ResourceProvider, id: Int, arg: Any): String {
                return "mocked"
            }
        }
        val list = listOf(
            VerseDomain.Base(1, 1001, "verse1"),
            VerseDomain.Next
        )
        val expected = VersesDomain.Success(list, buildString, 0)
        val actual = mapper.map(
            Triple(
                listOf(VerseData.Base(1, 1001, "verse1")),
                buildString,
                Pair(0, false)
            )
        )
        assertEquals(expected, actual)
    }
}