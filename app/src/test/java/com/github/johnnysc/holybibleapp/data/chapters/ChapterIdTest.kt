package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.DbWrapper
import io.realm.RealmObject
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.exp

/**
 * @author Asatryan on 17.07.2021
 */
class ChapterIdTest {

    @Test
    fun test_real_id() {
        val chapterId = ChapterId.Base(5, 6)
        val actual = chapterId.map(object : ChapterIdToUiMapper<Int> {
            override fun map(realId: Int) = realId
        })
        val expected = 6
        assertEquals(expected, actual)
    }

    @Test
    fun test_real_id_by_generated() {
        val chapterId = ChapterId.Base(5, 0, 5006)
        val actual = chapterId.map(object : ChapterIdToUiMapper<Int> {
            override fun map(realId: Int) = realId
        })
        val expected = 6
        assertEquals(expected, actual)
    }

    @Test
    fun test_generated_id() {
        val chapterId = ChapterId.Base(5, 6)
        val actual = chapterId.map(object : DbWrapper<TestRealmObject> {
            override fun createObject(id: Int): TestRealmObject {
                return TestRealmObject().apply {
                    this.id = id
                }
            }
        })
        val expectedId = 5006
        assertEquals(expectedId, actual.id)
    }

    @Test
    fun test_generated_id_by_real() {
        val chapterId = ChapterId.Base(5, 0, 5006)
        val actual = chapterId.map(object : DbWrapper<TestRealmObject> {
            override fun createObject(id: Int): TestRealmObject {
                return TestRealmObject().apply {
                    this.id = id
                }
            }
        })
        val expectedId = 5006
        assertEquals(expectedId, actual.id)
    }

    @Test
    fun test_limits() {
        val chapterId = ChapterId.Base(7)
        val actual = Pair(chapterId.min(), chapterId.max())
        val expected = Pair(7000, 8000)
        assertEquals(expected, actual)
    }

    private inner class TestRealmObject : RealmObject() {
        var id: Int = -1
    }
}
