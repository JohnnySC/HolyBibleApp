package com.github.johnnysc.holybibleapp.presentation.books

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookUi {

    fun <T> map(mapper: BookUiMapper<T>): T = mapper.map(0, "", false)

    object Empty : BookUi
    object Progress : BookUi

    data class Base(
        private val id: Int, private val name: String, private val isFavorite: Boolean = false
    ) : BookUi {
        override fun <T> map(mapper: BookUiMapper<T>) = mapper.map(id, name, isFavorite)
    }

    data class Testament(
        private val id: Int, private val name: String, private val collapsed: Boolean = false
    ) : BookUi {
        override fun <T> map(mapper: BookUiMapper<T>) = mapper.map(id, name, collapsed)
    }

    data class Fail(private val message: String) : BookUi {
        override fun <T> map(mapper: BookUiMapper<T>) =
            mapper.map(-1, message, false)
    }
}