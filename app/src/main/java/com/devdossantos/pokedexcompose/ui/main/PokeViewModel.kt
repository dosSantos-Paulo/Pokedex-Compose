package com.devdossantos.pokedexcompose.ui.main


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devdossantos.pokedex.domain.model.pokemon.PokemonModel
import com.devdossantos.pokedex.data.api.utils.Constants.DEFAULT_API_LIMIT
import com.devdossantos.pokedex.data.api.utils.Constants.DEFAULT_API_OFFSET
import com.devdossantos.pokedex.domain.usecase.GetPokemonUseCase
import com.devdossantos.pokedex.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class PokeViewModel(
    private val _useCase: GetPokemonUseCase
) : ViewModel() {

    fun getList() = liveData(Dispatchers.IO) {
        val pokemonList = mutableListOf<PokemonModel>()
        Log.d("ktor", "-> liveData")
        _useCase.getPokemonList(
            DEFAULT_API_LIMIT,
            DEFAULT_API_OFFSET
        ).collect {result ->
            when(result){
                is Result.Success -> {
                    result.data.results.forEach {
                        val pokemon = getPokemonByName(it.name)
                        if (pokemon != null) {
                            pokemonList.add(pokemon)
                        }
                    }
                    emit(pokemonList)
                }
                is Result.Error -> {
                    Log.d("ERROR", "api error -> ${result.error}")
                }
            }
        }

    }

    suspend fun getPokemonByName(name: String): PokemonModel? {
        var pokemon: PokemonModel? = null

        _useCase.getPokemonByName(name).collect { result ->
            when(result){
                is Result.Success -> {
                    pokemon = result.data
                }
                is Result.Error -> {
                    Log.d("ERROR", "api error -> ${result.error}")
                }
            }
        }

        return pokemon
    }

}