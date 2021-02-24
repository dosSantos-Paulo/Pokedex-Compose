package com.devdossantos.pokedexcompose.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devdossantos.pokedex.data.api.model.pokemon.PokemonModel
import com.devdossantos.pokedex.data.api.repository.KtorRepository
import com.devdossantos.pokedex.data.api.utils.Constants.DEFAULT_API_LIMIT
import com.devdossantos.pokedex.data.api.utils.Constants.DEFAULT_API_OFFSET
import kotlinx.coroutines.Dispatchers

class PokeViewModel(
    private val _repository: KtorRepository
) : ViewModel() {

    fun getList() = liveData(Dispatchers.IO) {
        val pokemonList = mutableListOf<PokemonModel>()
        Log.d("ktor", "-> liveData")
        val result = _repository.getPokemonList(
            DEFAULT_API_LIMIT,
            DEFAULT_API_OFFSET
        )
        Log.d("ktor", "result ok -> ${result.results[0].name}")
        result.results.forEach {
            pokemonList.add(getPokemonByName(it.name))
        }
        emit(pokemonList)
    }

    suspend fun getPokemonByName(name: String): PokemonModel {
        return _repository.getPokemonByName(name)
    }

}