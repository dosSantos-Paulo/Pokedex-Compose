package com.devdossantos.pokedex.domain.repository

import com.devdossantos.pokedex.domain.entity.PokemonEntity

interface DataBaseRepository {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : List<PokemonEntity>
}