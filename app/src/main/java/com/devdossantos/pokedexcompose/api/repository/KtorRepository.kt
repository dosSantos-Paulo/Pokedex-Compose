package com.devdossantos.pokedexcompose.api.repository

import com.devdossantos.pokedexcompose.api.model.response.ApiResponseModel
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.api.utils.KtorClient
import io.ktor.client.request.*


class KtorRepository: KtorEndpoint {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel {
        val url = ("api/v2/pokemon?limit=$limit&offset=$offset")
        return KtorClient.createHttpClient(url).get()
    }

    override suspend fun getPokemonByName(name: String): PokemonModel {
        val url = ("api/v2/pokemon/$name")
        return KtorClient.createHttpClient(url).get()
    }
}