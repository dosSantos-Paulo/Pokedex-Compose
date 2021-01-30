package com.devdossantos.pokedexcompose.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val _pokeViewModel: PokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _pokeViewModel.getList().observe(this) {
            it.results.forEach {


                _pokeViewModel.getPokemonByName(it.name).observe(this) {pokemon ->
                    Log.d("API", "Name: ${pokemon.name}")
                    Log.d("API", "Id: ${pokemon.id}")
                    Log.d("API", "Height: ${pokemon.height}")
                    Log.d("API", "Weight: ${pokemon.weight}")

                    pokemon.stats.forEach {
                        Log.d("API", "${it.stat.name} -> ${it.base_stat}")
                    }

                    pokemon.types.forEach {
                        Log.d("API", "tipe: ${it.type.name}")
                    }
                    Log.d("API", "===================================")
                }

            }

        }


        setContent {
            MainActivity("Rodando")
        }

    }
}


@Composable
fun MainActivity(name: String) {

    Text(text = name)
}