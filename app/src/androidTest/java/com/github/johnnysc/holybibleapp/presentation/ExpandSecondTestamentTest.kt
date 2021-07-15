package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class ExpandSecondTestamentTest : BaseTest() {

    @Test
    fun test() {
        BooksPage().run {
            firstBookNewTestament.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestament.checkDoesntExist()

            tap(newTestamentPositionIfFirstExpanded)
            firstBookOldTestament.checkVisible()
        }
    }
}