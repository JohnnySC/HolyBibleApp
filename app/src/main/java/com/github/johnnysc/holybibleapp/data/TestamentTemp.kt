package com.github.johnnysc.holybibleapp.data

/**
 * @author Asatryan on 03.07.2021
 **/
interface TestamentTemp {
    fun save(testament: String)
    fun matches(testament: String): Boolean
    fun isEmpty(): Boolean

    class Base : TestamentTemp {
        private var temp: String = ""
        override fun save(testament: String) {
            temp = testament
        }
        override fun matches(testament: String) = temp == testament
        override fun isEmpty() = temp.isEmpty()
    }
}