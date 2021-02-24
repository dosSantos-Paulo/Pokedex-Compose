package com.devdossantos.pokedex.data.api.model

data class ApiResponseModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)