package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel

interface GetPokemonUseCase {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel
    suspend fun getPokemonByName(name: String): PokemonModel
}