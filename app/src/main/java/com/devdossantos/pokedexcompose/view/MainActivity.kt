package com.devdossantos.pokedexcompose.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                    PokeList(it)
                }
            }
        }
    }

    @Composable
    private fun PokeList(pokemonList: MutableList<PokemonModel>) {
        val context = ContextAmbient.current
        LazyColumnFor(items = pokemonList) { pokemon ->
            PokemonRow(pokemon = pokemon, onPokeClick = {
                Toast.makeText(context, "You clicked ${pokemon.name}", Toast.LENGTH_LONG).show()
            })
            Divider()
        }
    }

    @Composable
    private fun PokemonRow(pokemon: PokemonModel, onPokeClick: (PokemonModel) -> Unit) {

        Row(
            modifier = Modifier
                .clickable(onClick = { onPokeClick(PokemonModel()) })
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            val imageModifier = Modifier
                .preferredSize(46.dp)
                .clip(shape = CircleShape)
            val image = imageResource(id = R.drawable.abc_ic_star_half_black_48dp)

//            Image(asset = image, modifier = imageModifier, contentScale = ContentScale.Crop)

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = pokemon.name.toString(),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Providers(AmbientContentAlpha provides ContentAlpha.high) {
                    Text(text = pokemon.id.toString(), style = MaterialTheme.typography.body2)
                }
            }
        }
    }

}
