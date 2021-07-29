package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import org.junit.Test

/**
 * @author Asatryan on 29.07.2021
 **/
class ScrollPositionTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(false)
        startWithScreenId(0)
    }

    @Test
    fun testcase_id01() {
        BooksPage().firstBookOldTestamentRussian.run {
            checkVisible()
            scrollUp()
            checkDoesntExist()

            killAppAndReturn()
            checkDoesntExist()
        }
    }
}