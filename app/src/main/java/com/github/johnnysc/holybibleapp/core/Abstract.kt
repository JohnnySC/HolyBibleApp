package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 26.06.2021
 **/
abstract class Abstract {

    abstract class Object<T, M : Mapper> {

        abstract fun map(mapper: M): T
    }

    // FIXME: 27.06.2021 rename
    interface Mapable<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper
    }
}