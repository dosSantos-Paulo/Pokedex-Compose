package com.devdossantos.pokedex.domain.repository

import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.model.response.ApiResponseModel

interface ApiRepository {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : ApiResponseModel

    suspend fun getPokemonByName(
        name: String
    ) : PokemonModel
}