package com.github.johnnysc.holybibleapp.domain.verses

/**
 * @author Asatryan on 17.07.2021
 **/
interface VerseDomainToUiMapper<T> {

    fun map(id: Int, text: String): T
}