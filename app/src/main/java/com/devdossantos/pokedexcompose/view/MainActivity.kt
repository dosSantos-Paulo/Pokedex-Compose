package com.devdossantos.pokedexcompose.view

import android.os.Bundle
import android.text.Layout
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
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
                MaterialTheme(
                    colors = lightColors()
                ) {
                    RecycledList(it)
                }
            }
        }
    }

    @Composable
    fun RecycledList(pokemonList: MutableList<PokemonModel>) {
        LazyColumn {
            items(items = pokemonList, itemContent = { pokemon ->
                Text(text = "nome: ${pokemon.name}")
            })
        }
        Divider()

    }

}
