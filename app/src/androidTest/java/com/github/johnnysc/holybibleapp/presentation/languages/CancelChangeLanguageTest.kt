package com.github.johnnysc.holybibleapp.presentation.languages

import androidx.test.uiautomator.UiDevice
import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.MainPage
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import org.junit.Test

/**
 * @author Asatryan on 28.07.2021
 **/
class CancelChangeLanguageTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(true)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id05() {
        val booksPage = BooksPage()
        booksPage.run {
            titleEnglish.checkVisible()
        }

        MainPage().menu.performTap()

        val languagesPage = LanguagesPage()
        languagesPage.run {
            titleEnglish.checkVisible()
            english isChecked true
            russian isChecked false
        }

        goBack()

        booksPage.titleEnglish.checkVisible()

    }

    @Test
    fun testcase_id06() {
        val booksPage = BooksPage()
        booksPage.run {
            titleEnglish.checkVisible()
        }

        MainPage().menu.performTap()

        LanguagesPage().run {
            titleEnglish.checkVisible()
            english isChecked true
            russian isChecked false
        }

        killAppAndReturn()

        booksPage.titleEnglish.checkVisible()
    }
}