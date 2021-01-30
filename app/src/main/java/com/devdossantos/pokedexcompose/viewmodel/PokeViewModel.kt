package com.devdossantos.pokedexcompose.viewmodel


import androidx.lifecycle.ViewModel
import com.devdossantos.pokedexcompose.api.repository.PokeRepository
import androidx.lifecycle.liveData
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import kotlinx.coroutines.Dispatchers

class PokeViewModel(
    private val _repository: PokeRepository
) : ViewModel() {

    fun getList() = liveData(Dispatchers.IO) {
        val pokemonList = mutableListOf<PokemonModel>()
        val result = _repository.getPokemonList()
        result.results.forEach {
            pokemonList.add(getPokemonByName(it.name))
        }
        emit(pokemonList)
    }

    suspend fun getPokemonByName(name: String): PokemonModel {
        return _repository.getPokemonByName(name)
    }

}