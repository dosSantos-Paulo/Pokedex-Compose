package com.devdossantos.pokedexcompose.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity

@Dao
interface BaseDao {
    @Insert
    suspend fun addPokemon(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon_list WHERE id = :id")
    suspend fun deletePokemon(id: Int)

    @Query("SELECT * FROM pokemon_list")
    suspend fun getAllPokemons(): List<PokemonEntity>

}