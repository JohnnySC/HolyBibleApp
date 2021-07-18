package com.github.johnnysc.holybibleapp.presentation.verses

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesUi {

    abstract fun map(mapper: VersesCommunication)

    class Base(private val list: List<VerseUi>) : VersesUi() {
        override fun map(mapper: VersesCommunication) = mapper.map(list)
    }
}