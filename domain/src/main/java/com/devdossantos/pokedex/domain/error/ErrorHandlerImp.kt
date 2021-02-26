package com.devdossantos.pokedex.domain.error

import java.lang.NullPointerException
import java.nio.channels.UnresolvedAddressException
import javax.net.ssl.SSLException

class ErrorHandlerImp : ErrorHandler {
    override suspend fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable){
            is UnresolvedAddressException -> ErrorEntity.ApiError.Network
            is SSLException -> ErrorEntity.ApiError.Network
            is NullPointerException -> ErrorEntity.ApiError.Unknown
            else -> ErrorEntity.Unknown
        }
    }
}