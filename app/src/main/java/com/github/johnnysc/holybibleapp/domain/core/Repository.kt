package com.github.johnnysc.holybibleapp.domain.core

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ChangeFavorite

/**
 * @author Asatryan on 05.08.2021
 **/
interface Repository<E : Abstract.DataObject> : ChangeFavorite<Int> {
    suspend fun fetchData(): E
}