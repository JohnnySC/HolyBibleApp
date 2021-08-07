package com.github.johnnysc.holybibleapp.sl.core

import com.github.johnnysc.holybibleapp.domain.core.Repository

/**
 * @author Asatryan on 30.07.2021
 **/
interface RepositoryContainer<T : Repository<*>> {

    fun repository(): T
}