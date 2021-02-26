package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.entity.PokemonEntity
import com.devdossantos.pokedex.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetDataBaseUseCase {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : Flow<Result<List<PokemonEntity>>>
}