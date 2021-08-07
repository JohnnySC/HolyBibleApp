package com.github.johnnysc.holybibleapp.domain.books

import androidx.annotation.StringRes
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ResourceProvider

/**
 * @author Asatryan on 03.07.2021
 **/
interface BookDomainToUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, name: String, isFavorite: Boolean = false): T

    class Name(
        private val resourceProvider: ResourceProvider,
        @StringRes private val stringResId: Int,
        private val arg: Any
    ) : BookDomainToUiMapper<String> {
        override fun map(id: Int, name: String, isFavorite: Boolean) =
            resourceProvider.string(stringResId, name, arg)
    }
}