package com.devdossantos.pokedex.domain.database.repository

import com.devdossantos.pokedex.domain.database.entity.PokemonEntity

interface DataBaseRepository {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : List<PokemonEntity>
}