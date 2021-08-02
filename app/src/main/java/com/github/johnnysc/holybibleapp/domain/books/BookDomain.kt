package com.github.johnnysc.holybibleapp.domain.books

/**
 * @author Asatryan on 03.07.2021
 **/
sealed class BookDomain {

    abstract fun <T> map(mapper: BookDomainToUiMapper<T>): T

    data class Base(
        private val id: Int,
        private val name: String,
        private val isFavorite: Boolean = false
    ) : BookDomain() {
        override fun <T> map(mapper: BookDomainToUiMapper<T>) = mapper.map(id, name, isFavorite)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun <T> map(mapper: BookDomainToUiMapper<T>) = type.map(mapper)
    }
}