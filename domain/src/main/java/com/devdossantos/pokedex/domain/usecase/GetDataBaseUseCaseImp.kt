package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.entity.PokemonEntity
import com.devdossantos.pokedex.domain.error.ErrorHandlerImp
import com.devdossantos.pokedex.domain.repository.DataBaseRepository
import com.devdossantos.pokedex.domain.utils.Result
import com.devdossantos.pokedex.domain.utils.handleResult
import kotlinx.coroutines.flow.Flow

class GetDataBaseUseCaseImp(
    private val _repository : DataBaseRepository,
    private val _errorHandler : ErrorHandlerImp
) : GetDataBaseUseCase {
    override suspend fun addPokemon(pokemon: PokemonEntity) {
        return _repository.addPokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        return _repository.deletePokemon(id)
    }

    override suspend fun getAllPokemons(): Flow<Result<List<PokemonEntity>>> {
        return _repository.getAllPokemons().handleResult(_errorHandler)
    }

}