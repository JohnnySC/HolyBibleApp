package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.BooksPage
import org.junit.Test

/**
 * @author Asatryan on 19.07.2021
 **/
class LanguagesChooseTest : BaseTest() {

    @Test
    fun test_nothing_checked_at_start() {
        LanguagesPage().run {
            title.checkVisible()
            english isChecked false
            russian isChecked false
        }
    }

    @Test
    fun test_exit() {
        LanguagesPage().run {
            title.checkVisible()

            goBack()

            checkAppNotShown(title)
        }
    }

    @Test
    fun testcase_id01() {
        LanguagesPage().run {
            title.checkVisible()
            english isChecked false
            russian isChecked false

            english.performTap()
        }

        BooksPage().run {
            title.checkVisible()
        }
    }

    @Test
    fun testcase_id02() {
        LanguagesPage().run {
            title.checkVisible()
            english isChecked false
            russian isChecked false

            english.performTap()
        }

        killAppAndReturn()

        BooksPage().run {
            title.checkVisible()
        }
    }
}