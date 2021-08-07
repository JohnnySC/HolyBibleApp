package com.github.johnnysc.holybibleapp.domain.core

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Asatryan on 05.08.2021
 **/
abstract class BaseDataToDomainMapper<S, R> : Abstract.Mapper.DataToDomain<S, R> {
    protected fun errorType(e: Exception) = when (e) {
        is UnknownHostException -> ErrorType.NO_CONNECTION
        is HttpException -> ErrorType.SERVICE_UNAVAILABLE
        else -> ErrorType.GENERIC_ERROR
    }
}