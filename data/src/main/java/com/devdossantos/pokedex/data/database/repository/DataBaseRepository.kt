package com.devdossantos.pokedex.data.database.repository

import com.devdossantos.pokedex.data.database.dao.BaseDao
import com.devdossantos.pokedex.data.database.entity.PokemonEntity

class DataBaseRepository(private val baseDao: BaseDao) {

    suspend fun addPokemon(pokemon: PokemonEntity) = baseDao.addPokemon(pokemon)


    suspend fun deletePokemon(id: Int) = baseDao.deletePokemon(id)


    suspend fun getAllPokemons() = baseDao.getAllPokemons()
}