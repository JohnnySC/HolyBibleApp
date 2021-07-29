package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.books.BooksPage
import org.junit.Test

/**
 * @author Asatryan on 19.07.2021
 **/
class LanguagesChooseTest : BaseTest() {

    @Test
    fun testcase_id04() {
        LanguagesPage().run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false
        }
        killAppAndReturn()
        LanguagesPage().run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false
        }
    }

    @Test
    fun test_exit_at_start() {
        LanguagesPage().run {
            titleEnglish.checkVisible()

            goBack()

            checkAppNotShown(titleEnglish)
        }
    }

    @Test
    fun testcase_id01() {
        LanguagesPage().run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false

            english.performTap()
        }

        BooksPage().run {
            titleEnglish.checkVisible()
        }
    }

    @Test
    fun testcase_id02() {
        LanguagesPage().run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false

            english.performTap()
        }

        BooksPage().run {
            titleEnglish.checkVisible()
            killAppAndReturn()
            titleEnglish.checkVisible()
        }
    }
}