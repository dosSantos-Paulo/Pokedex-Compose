package com.devdossantos.pokedex.domain.api.usecase

import com.devdossantos.pokedex.domain.api.model.ApiResponseModel
import com.devdossantos.pokedex.domain.api.model.pokemon.PokemonModel

interface GetPokemonUseCase {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel
    suspend fun getPokemonByName(name: String): PokemonModel
}