package com.devdossantos.pokedexcompose.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdossantos.pokedexcompose.api.repository.PokeRepository
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class PokeViewModel(
    private val _repository: PokeRepository
) : ViewModel() {


    fun getList() = liveData(Dispatchers.IO) {
        try {
            val result = _repository.getPokemonList()
            emit(result)
        } catch (ex: Exception) {
            Log.e("API", "getList() error -> ${ex.message}")
        }


    }

    fun getPokemonByName (name: String) = liveData(Dispatchers.IO) {
        try {
            val list = _repository.getPokemonByName(name)
            emit(list)
        } catch (ex: Exception) {
            Log.e("API", "getPokemonByName() error -> ${ex.message}")
        }

    }

    class PokeViewModelFactory(
        private val _repository: PokeRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokeViewModel(_repository) as T
        }

    }
}