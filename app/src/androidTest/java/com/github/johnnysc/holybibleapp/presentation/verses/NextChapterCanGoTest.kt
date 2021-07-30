package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import org.junit.After
import org.junit.Test

/**
 * @author Asatryan on 30.07.2021
 **/
class NextChapterCanGoTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(false)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id01() {
        val firstBookName = BooksPage().firstBookOldTestamentRussian
        tap(1)
        firstBookName.checkVisible()
        tap(0)
        val versesPage = VersesPage()
        versesPage.titleRussian(firstBookName, 1).checkVisible()
        versesPage.nextChapterRussian.performTap()
        versesPage.titleRussian(firstBookName, 2).checkVisible()
    }

    @After
    fun clear() {
        goBack()
        goBack()
    }
}