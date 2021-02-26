package com.devdossantos.pokedex.domain.repository

import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPokemonRepository {
    suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ) : Flow<ApiResponseModel>

    suspend fun getPokemonByName(
        name: String
    ) : Flow<PokemonModel>
}