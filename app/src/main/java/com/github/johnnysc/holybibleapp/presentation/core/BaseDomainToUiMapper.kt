package com.github.johnnysc.holybibleapp.presentation.core

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.ErrorType

/**
 * @author Asatryan on 05.08.2021
 **/
abstract class BaseDomainToUiMapper<S, T>(private val resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi<S, T> {

    protected fun errorMessage(errorType: ErrorType) = resourceProvider.string(
        when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
    )
}