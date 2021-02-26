package com.devdossantos.pokedexcompose.ui.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devdossantos.pokedex.domain.entity.PokemonEntity
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCase
import com.devdossantos.pokedex.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class DataBaseViewModel(
    private val _useCase: GetDataBaseUseCase
    ) : ViewModel() {

    fun addPokemon(pokemon: PokemonEntity) = liveData(Dispatchers.IO) {
        try {
            _useCase.addPokemon(pokemon)
            emit(true)
        }
        catch (ex: Exception) {
            println(ex.message)
            emit(false)
        }
    }

    fun deletePokemon(id: Int) = liveData(Dispatchers.IO) {
        try {
            _useCase.deletePokemon(id)
            emit(true)
        }
        catch (ex: Exception) {
            println(ex.message)
            emit(false)
        }

    }

    fun getAllPokemons() = liveData(Dispatchers.IO) {
        _useCase.getAllPokemons().collect { result ->
            when(result){
                is Result.Success -> {
                    emit(result.data)
                }
                is Result.Error -> {
                    Log.d("ERROR", "database error -> ${result.error}")
                }
            }
        }
    }
}