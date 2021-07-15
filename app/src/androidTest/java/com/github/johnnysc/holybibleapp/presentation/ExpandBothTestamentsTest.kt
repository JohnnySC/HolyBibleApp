package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class ExpandBothTestamentsTest : BaseTest() {

    @Test
    fun test_expand_first_then_second() {
        BooksPage().run {
            firstBookOldTestament.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()

            firstBookNewTestament.checkVisible()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestament.checkDoesntExist()


            firstBookOldTestament.checkDoesntExist()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkVisible()

            firstBookNewTestament.checkDoesntExist()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestament.checkVisible()
        }
    }

    @Test
    fun test_expand_second_then_first() {
        BooksPage().run {
            firstBookNewTestament.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestament.checkDoesntExist()

            firstBookOldTestament.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()


            firstBookNewTestament.checkDoesntExist()
            tap(newTestamentPositionIfFirstCollapsed)
            firstBookNewTestament.checkVisible()

            firstBookOldTestament.checkDoesntExist()
            tap(oldTestamentPosition)
            firstBookOldTestament.checkVisible()
        }
    }
}