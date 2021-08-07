package com.github.johnnysc.holybibleapp.presentation.core

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.presentation.books.BookUiMapper

/**
 * @author Asatryan on 12.07.2021
 **/
interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>, BookUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
        map(isFavoriteOrCollapsed)
}