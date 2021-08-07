package com.github.johnnysc.holybibleapp.presentation.deeplink

/**
 * @author Asatryan on 05.08.2021
 **/
interface DeeplinkData {

    fun shareText(left: String, right: String): String
    fun ids(data: String): List<Int>

    class Base(private val url: String) : DeeplinkData {

        override fun shareText(left: String, right: String) = "$left$url$right"

        override fun ids(data: String): List<Int> {
            val ids = if (data.length > url.length) data.substring(url.length) else ""
            return if (ids.contains(DELIMITER) && ids.replace(DELIMITER, "").isNotEmpty())
                ids.split(DELIMITER).mapNotNull { it.toIntOrNull() }
            else
                emptyList()
        }

        private companion object {
            const val DELIMITER = "_"
        }
    }
}