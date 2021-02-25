package com.devdossantos.pokedex.data.database.repository

import com.devdossantos.pokedex.data.database.dao.BaseDao
import com.devdossantos.pokedex.domain.database.entity.PokemonEntity
import com.devdossantos.pokedex.domain.database.repository.DataBaseRepository

class DataBaseRepositoryImp(private val baseDao: BaseDao): DataBaseRepository {

    override suspend fun addPokemon(pokemon: PokemonEntity) = baseDao.addPokemon(pokemon)

    override suspend fun deletePokemon(id: Int) = baseDao.deletePokemon(id)

    override suspend fun getAllPokemons() = baseDao.getAllPokemons()
}