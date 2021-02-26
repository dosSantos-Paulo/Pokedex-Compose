package com.devdossantos.pokedex.data.api.repository

import com.devdossantos.pokedex.data.api.utils.KtorClient
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.model.response.ApiResponseModel
import com.devdossantos.pokedex.domain.repository.ApiRepository
import io.ktor.client.request.*

class ApiRepositoryImp: ApiRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel {
        val url = ("api/v2/pokemon?limit=$limit&offset=$offset")
        return KtorClient.createHttpClient(url).get()
    }

    override suspend fun getPokemonByName(name: String): PokemonModel {
        val url = ("api/v2/pokemon/$name")
        return KtorClient.createHttpClient(url).get()
    }
}