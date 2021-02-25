package com.devdossantos.pokedex.data.api.repository

import com.devdossantos.pokedex.domain.api.model.ApiResponseModel
import com.devdossantos.pokedex.domain.api.model.pokemon.PokemonModel
import com.devdossantos.pokedex.data.api.utils.KtorClient
import com.devdossantos.pokedex.domain.api.repository.GetPokemonRepository
import io.ktor.client.request.*


class GetPokemonImp: GetPokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponseModel {
        val url = ("api/v2/pokemon?limit=$limit&offset=$offset")
        return KtorClient.createHttpClient(url).get()
    }

    override suspend fun getPokemonByName(name: String): PokemonModel {
        val url = ("api/v2/pokemon/$name")
        return KtorClient.createHttpClient(url).get()
    }
}