package com.devdossantos.pokedexcompose.api.repository

import com.devdossantos.pokedexcompose.api.model.response.ApiResponseModel
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.api.utils.Constants.DEFAULT_API_LIMIT
import com.devdossantos.pokedexcompose.api.utils.Constants.DEFAULT_API_OFFSET
import com.devdossantos.pokedexcompose.api.utils.NetworkUtils
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeEndpoint {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList (
        @Query("limit") limit: Int = DEFAULT_API_LIMIT,
        @Query("offset") offset: Int = DEFAULT_API_OFFSET,
    ) : ApiResponseModel

    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemonByName (
        @Path("name") name: String
    ) : PokemonModel


    companion object {
        val ENDPOINT: PokeEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(PokeEndpoint::class.java)
        }
    }
}