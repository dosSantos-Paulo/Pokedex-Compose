package com.devdossantos.pokedexcompose.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.database.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers

class DataBaseViewModel(private val _repository: DataBaseRepository) : ViewModel() {

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

    class DataBaseViewModelFactory(private val _repo: DataBaseRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DataBaseViewModel(_repo) as T
        }

    }
}