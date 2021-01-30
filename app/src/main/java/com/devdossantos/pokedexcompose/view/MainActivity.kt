package com.devdossantos.pokedexcompose.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val _pokeViewModel: PokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getList()

    }

    private fun getList() {
        _pokeViewModel.getList().observe(this) {

            setContent {
                MainActivity("$it")

            }
        }
    }
}


@Composable
fun MainActivity(name: String) {

    Text(text = name)
}