package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class CollapseBothTestamentsTest : BaseTest() {

    @Test
    fun test_collapse_first_then_second() {
        BooksPage().run {
            firstBookOldTestamentEnglish.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkDoesntExist()

            firstBookNewTestamentEnglish.checkVisible()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestamentEnglish.checkDoesntExist()
        }
    }

    @Test
    fun test_collapse_second_then_first() {
        BooksPage().run {
            firstBookNewTestamentEnglish.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestamentEnglish.checkDoesntExist()

            firstBookOldTestamentEnglish.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkDoesntExist()
        }
    }
}