package com.devdossantos.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devdossantos.pokedex.domain.entity.PokemonEntity

@Dao
interface BaseDao {
    @Insert
    suspend fun addPokemon(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon WHERE id = :id")
    suspend fun deletePokemon(id: Int)

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons(): List<PokemonEntity>

}