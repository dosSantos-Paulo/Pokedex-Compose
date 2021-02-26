package com.devdossantos.pokedex.data.database.repository

import com.devdossantos.pokedex.data.database.dao.BaseDao
import com.devdossantos.pokedex.domain.entity.PokemonEntity
import com.devdossantos.pokedex.domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DataBaseRepositoryImp(private val baseDao: BaseDao): DataBaseRepository {

    override suspend fun addPokemon(pokemon: PokemonEntity) = baseDao.addPokemon(pokemon)

    override suspend fun deletePokemon(id: Int) = baseDao.deletePokemon(id)

    override suspend fun getAllPokemons() = flowOf(baseDao.getAllPokemons() )
}