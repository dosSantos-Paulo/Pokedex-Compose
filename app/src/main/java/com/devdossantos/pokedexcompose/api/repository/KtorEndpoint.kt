package com.devdossantos.pokedexcompose.api.repository

import com.devdossantos.pokedexcompose.api.model.response.ApiResponseModel
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel

interface KtorEndpoint {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : ApiResponseModel

    suspend fun getPokemonByName(
        name: String
    ) : PokemonModel
}