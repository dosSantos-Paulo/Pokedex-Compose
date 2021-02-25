package com.devdossantos.pokedex.domain.api.repository

import com.devdossantos.pokedex.domain.api.model.ApiResponseModel
import com.devdossantos.pokedex.domain.api.model.pokemon.PokemonModel

interface GetPokemonRepository {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : ApiResponseModel

    suspend fun getPokemonByName(
        name: String
    ) : PokemonModel
}