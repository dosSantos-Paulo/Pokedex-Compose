package com.devdossantos.pokedexcompose.api.repository


class PokeRepository {
    private val _client = PokeEndpoint.ENDPOINT

    suspend fun getPokemonList() = _client.getPokemonList()

    suspend fun getPokemonByName(name: String) = _client.getPokemonByName(name)

}