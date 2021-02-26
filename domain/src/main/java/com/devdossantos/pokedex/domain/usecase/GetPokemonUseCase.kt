package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetPokemonUseCase {
    suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<ApiResponseModel>>
    suspend fun getPokemonByName(name: String): Flow<Result<PokemonModel>>
}