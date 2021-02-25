package com.devdossantos.pokedex.domain.api.usecase

import com.devdossantos.pokedex.domain.api.model.ApiResponseModel
import com.devdossantos.pokedex.domain.api.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.api.repository.GetPokemonRepository

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