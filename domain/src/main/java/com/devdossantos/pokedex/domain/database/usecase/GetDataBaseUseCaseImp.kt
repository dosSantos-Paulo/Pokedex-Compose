package com.devdossantos.pokedex.domain.database.usecase

import com.devdossantos.pokedex.domain.database.entity.PokemonEntity
import com.devdossantos.pokedex.domain.database.repository.DataBaseRepository

class GetDataBaseUseCaseImp(
    private val _repository : DataBaseRepository
) : GetDataBaseUseCase{
    override suspend fun addPokemon(pokemon: PokemonEntity) {
        return _repository.addPokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        return _repository.deletePokemon(id)
    }

    override suspend fun getAllPokemons(): List<PokemonEntity> {
        return _repository.getAllPokemons()
    }

}