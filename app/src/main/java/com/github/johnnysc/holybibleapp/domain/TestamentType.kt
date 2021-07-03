package com.github.johnnysc.holybibleapp.domain

/**
 * @author Asatryan on 03.07.2021
 **/
enum class TestamentType(private val id: Int) {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    fun getId() = id
}