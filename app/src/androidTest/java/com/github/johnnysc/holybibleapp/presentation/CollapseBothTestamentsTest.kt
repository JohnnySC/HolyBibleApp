package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class CollapseBothTestamentsTest : BaseTest() {

    @Test
    fun test_collapse_first_then_second() {
        BooksPage().run {
            firstBookOldTestament.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()

            firstBookNewTestament.checkVisible()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestament.checkDoesntExist()
        }
    }

    @Test
    fun test_collapse_second_then_first() {
        BooksPage().run {
            firstBookNewTestament.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestament.checkDoesntExist()

            firstBookOldTestament.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()
        }
    }
}