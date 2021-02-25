package com.devdossantos.pokedexcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devdossantos.pokedex.domain.entity.PokemonEntity
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCase
import kotlinx.coroutines.Dispatchers

class DataBaseViewModel(
    private val _repository: GetDataBaseUseCase
    ) : ViewModel() {

    fun addPokemon(pokemon: PokemonEntity) = liveData(Dispatchers.IO) {
        try {
            _repository.addPokemon(pokemon)
            emit(true)
        }
        catch (ex: Exception) {
            println(ex.message)
            emit(false)
        }
    }

    fun deletePokemon(id: Int) = liveData(Dispatchers.IO) {
        try {
            _repository.deletePokemon(id)
            emit(true)
        }
        catch (ex: Exception) {
            println(ex.message)
            emit(false)
        }

    }

    fun getAllPokemons() = liveData(Dispatchers.IO) {
        try {
            val list = _repository.getAllPokemons()
            emit(list)
        }
        catch (ex: Exception) {
            println(ex.message)
        }
    }
}