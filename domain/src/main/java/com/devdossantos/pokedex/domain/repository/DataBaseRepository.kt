package com.devdossantos.pokedex.domain.repository

import com.devdossantos.pokedex.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : Flow<List<PokemonEntity>>
}