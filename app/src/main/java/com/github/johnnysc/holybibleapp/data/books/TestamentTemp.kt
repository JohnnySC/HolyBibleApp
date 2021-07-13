package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Matcher
import com.github.johnnysc.holybibleapp.core.Save

/**
 * @author Asatryan on 03.07.2021
 **/
interface TestamentTemp : Matcher<String>, Save<String> {
    fun isEmpty(): Boolean

    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(data: String) {
            temp = data
        }
        override fun matches(arg: String) = temp == arg
        override fun isEmpty() = temp.isEmpty()
    }
}