package com.devdossantos.pokedexcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devdossantos.pokedex.domain.database.entity.PokemonEntity
import com.devdossantos.pokedex.data.database.repository.DataBaseRepositoryImp
import com.devdossantos.pokedex.domain.database.repository.DataBaseRepository
import com.devdossantos.pokedex.domain.database.usecase.GetDataBaseUseCase
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