package com.github.johnnysc.holybibleapp.presentation

import org.junit.Test

/**
 * @author Asatryan on 15.07.2021
 **/
class SavingCollapsedTest : BaseTest() {

    /**
     * For now running with some instrumentation screen between
     * so can be passed in debug mode with breakpoint at last line
     * BaseTest#killAppAndRerun
     */
    @Test
    fun test() {
        BooksPage().run {
            tap(oldTestamentPosition)
            firstBookOldTestament.checkDoesntExist()

            //todo find a better method
            killAppAndReturn()

            firstBookOldTestament.checkDoesntExist()
        }
    }
}