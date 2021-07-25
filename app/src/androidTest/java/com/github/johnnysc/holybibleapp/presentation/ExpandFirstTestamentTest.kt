package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class ExpandFirstTestamentTest : BaseTest() {

    @Test
    fun test() {
        BooksPage().run {
            firstBookOldTestamentEnglish.checkVisible()
            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkDoesntExist()

            tap(oldTestamentPosition)
            firstBookOldTestamentEnglish.checkVisible()
        }
    }
}