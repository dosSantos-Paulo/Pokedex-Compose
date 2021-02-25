package com.devdossantos.pokedex.domain.utils

import com.devdossantos.pokedex.domain.error.ErrorEntity

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorEntity) : Result<T>()
}