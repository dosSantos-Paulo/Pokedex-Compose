package com.devdossantos.pokedex.domain.database.usecase

import com.devdossantos.pokedex.domain.database.entity.PokemonEntity

interface GetDataBaseUseCase {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : List<PokemonEntity>
}