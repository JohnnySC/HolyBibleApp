package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersPage
import org.junit.Test

/**
 * @author Asatryan on 30.07.2021
 **/
class ScrollPositionTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(false)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id03() {
        val firstBookName = BooksPage().firstBookOldTestamentRussian
        tap(1)
        firstBookName.checkVisible()
        val chapterPage = ChaptersPage()
        val versesPage = VersesPage()

        tap(0)
        for (i in 0..15) {
            versesPage.titleRussian(firstBookName, i+1).checkVisible()
            versesPage.nextChapterRussian.performTap()
        }
        versesPage.titleRussian(firstBookName, 17).checkVisible()

        goBack()

        chapterPage.chapterRussian(18).checkVisible()
    }
}