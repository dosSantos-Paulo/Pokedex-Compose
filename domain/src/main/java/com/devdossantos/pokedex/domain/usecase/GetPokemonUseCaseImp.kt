package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.error.ErrorHandlerImp
import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.repository.GetPokemonRepository
import com.devdossantos.pokedex.domain.utils.Result
import com.devdossantos.pokedex.domain.utils.handleResult
import kotlinx.coroutines.flow.Flow

class GetPokemonUseCaseImp(
    private val _repository: GetPokemonRepository,
    private val _errorHandler: ErrorHandlerImp
): GetPokemonUseCase {

    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<Result<ApiResponseModel>> {
        return _repository.getPokemonList(limit, offset).handleResult(_errorHandler)
    }

    override suspend fun getPokemonByName(name: String): Flow<Result<PokemonModel>> {
        return _repository.getPokemonByName(name).handleResult(_errorHandler)
    }

}