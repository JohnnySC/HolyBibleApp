package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 27.07.2021
 **/
interface ListMapper<T> : Abstract.Mapper.Data<List<T>, Unit> {
    class Empty<T> : ListMapper<T> {
        override fun map(data: List<T>) = Unit
    }
}