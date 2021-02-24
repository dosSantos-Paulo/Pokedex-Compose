package com.devdossantos.pokedex.data.api.repository

import com.devdossantos.pokedex.data.api.model.ApiResponseModel
import com.devdossantos.pokedex.data.api.model.pokemon.PokemonModel

interface KtorEndpoint {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : ApiResponseModel

    suspend fun getPokemonByName(
        name: String
    ) : PokemonModel
}