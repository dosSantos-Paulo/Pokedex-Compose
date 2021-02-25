package com.devdossantos.pokedex.domain.usecase

import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.repository.GetPokemonRepository

class GetPokemonUseCaseImp(
    private val _repository: GetPokemonRepository
): GetPokemonUseCase {

    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel {
        return _repository.getPokemonList(limit, offset)
    }

    override suspend fun getPokemonByName(name: String): PokemonModel {
        return _repository.getPokemonByName(name)
    }

}