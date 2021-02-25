package com.devdossantos.pokedex.domain.model.response

data class ApiResponseModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)