package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class ExpandBothTestamentsTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(true)
        startWithScreenId(0)
    }

    @Test
    fun test_expand_first_then_second() {
        BooksPage().run {
            firstBookOldTestamentEnglish.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkDoesntExist()

            firstBookNewTestamentEnglish.checkVisible()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestamentEnglish.checkDoesntExist()


            firstBookOldTestamentEnglish.checkDoesntExist()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkVisible()

            firstBookNewTestamentEnglish.checkDoesntExist()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestamentEnglish.checkVisible()
        }
    }

    @Test
    fun test_expand_second_then_first() {
        BooksPage().run {
            firstBookNewTestamentEnglish.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestamentEnglish.checkDoesntExist()

            firstBookOldTestamentEnglish.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkDoesntExist()


            firstBookNewTestamentEnglish.checkDoesntExist()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestamentEnglish.checkVisible()

            firstBookOldTestamentEnglish.checkDoesntExist()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkVisible()
        }
    }
}