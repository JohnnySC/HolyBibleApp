package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import org.junit.After
import org.junit.Test

/**
 * @author Asatryan on 30.07.2021
 **/
class NextChapterLastTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(false)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id02() {
        val booksPage = BooksPage()
        val bookName = booksPage.fewChaptersBookRussian
        tap(booksPage.fewChaptersBookPosition)
        bookName.checkVisible()
        tap(3)
        val versesPage = VersesPage()
        versesPage.titleRussian(bookName, 4).checkVisible()
        versesPage.nextChapterRussian.checkDoesntExist()
    }

    @Test
    fun testcase_id02_long() {
        val booksPage = BooksPage()
        val bookName = booksPage.fewChaptersBookRussian
        tap(booksPage.fewChaptersBookPosition)
        bookName.checkVisible()
        val versesPage = VersesPage()

        tap(0)
        for (i in 0 until 3) {
            versesPage.titleRussian(bookName, i+1).checkVisible()
            versesPage.nextChapterRussian.performTap()
        }
        versesPage.titleRussian(bookName, 4).checkVisible()
        versesPage.nextChapterRussian.checkDoesntExist()
    }

    @After
    fun clear() {
        goBack()
        goBack()
    }
}