package com.devdossantos.pokedex.domain.error

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorEntity
}