package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.entity.PokemonEntity

interface GetDataBaseUseCase {
    suspend fun addPokemon(pokemon: PokemonEntity)
    suspend fun deletePokemon(id: Int)
    suspend fun getAllPokemons() : List<PokemonEntity>
}