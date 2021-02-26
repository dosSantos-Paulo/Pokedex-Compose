package com.devdossantos.pokedex.data.api.repository

import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.repository.ApiRepository
import com.devdossantos.pokedex.domain.repository.GetPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


class GetPokemonImp(
    private val _apiRepository : ApiRepository
) : GetPokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<ApiResponseModel> {
        return  flowOf(_apiRepository.getPokemonList(limit, offset))
    }

    override suspend fun getPokemonByName(name: String): Flow<PokemonModel> {
        return  flowOf(_apiRepository.getPokemonByName(name))
    }
}