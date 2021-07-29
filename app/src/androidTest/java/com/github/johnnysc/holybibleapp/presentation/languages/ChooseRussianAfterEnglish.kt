package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.MainPage
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import org.junit.After
import org.junit.Test

/**
 * @author Asatryan on 28.07.2021
 **/
class ChooseRussianAfterEnglish : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(true)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id03() {
        val booksPage = BooksPage()
        booksPage.titleEnglish.checkVisible()

        val mainPage = MainPage()
        mainPage.menu.performTap()

        val languagesPage = LanguagesPage()
        languagesPage.run {
            titleEnglish.checkVisible()
            english isChecked true
            russian isChecked false

            russian.performTap()
        }

        booksPage.titleRussian.checkVisible()

        mainPage.menu.performTap()

        languagesPage.run {
            titleRussian.checkVisible()
            english isChecked false
            russian isChecked true
        }
    }

    @After
    fun clear() {
        LanguagesPage().english.performTap()
    }
}