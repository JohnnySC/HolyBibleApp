package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.presentation.BaseTest
import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class ExpandSecondTestamentTest : BaseTest() {

    override fun doBeforeActivityStart() {
        super.doBeforeActivityStart()
        selectLanguage(true)
        startWithScreenId(0)
    }

    @Test
    fun test() {
        BooksPage().run {
            firstBookNewTestamentEnglish.checkVisible()
            tap(newTestamentPositionIfFirstExpanded)
            firstBookNewTestamentEnglish.checkDoesntExist()

            tap(newTestamentPositionIfFirstExpanded)
            firstBookOldTestamentEnglish.checkVisible()
        }
    }
}