package com.github.johnnysc.holybibleapp.presentation.languages

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import com.github.johnnysc.holybibleapp.presentation.MainPage
import org.junit.Test

/**
 * @author Asatryan on 28.07.2021
 **/
class GoToLanguagesScreenTwiceTest : BaseTest() {

    @Test
    fun testcase_id07() {
        val languagesPage = LanguagesPage()
        languagesPage.run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false
        }

        MainPage().menu.performTap()

        languagesPage.run {
            titleEnglish.checkVisible()
            english isChecked false
            russian isChecked false
        }
    }
}