package com.github.johnnysc.holybibleapp.core

/**
 * @author Asatryan on 26.06.2021
 **/
abstract class Abstract {

    interface Object<T, M : Mapper> {

        fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper
    }
}