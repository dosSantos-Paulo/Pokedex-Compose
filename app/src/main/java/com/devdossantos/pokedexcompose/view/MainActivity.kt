package com.devdossantos.pokedexcompose.view

import android.icu.lang.UCharacter.DecompositionType.SMALL
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


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
        }
    }

    @Composable
    private fun PokemonRow(pokemon: PokemonModel, onPokeClick: (PokemonModel) -> Unit) {
        var color = Color.White

        Row(
            modifier = Modifier

                .fillMaxWidth()
        ) {

            val imageModifier = Modifier
                .preferredSize(46.dp)
                .clip(shape = CircleShape)
            val image = imageResource(id = R.drawable.abc_ic_star_half_black_48dp)

            val type = pokemon.types?.get(0)?.type?.name.toString()

//            Image(asset = image, modifier = imageModifier, contentScale = ContentScale.Crop)


            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { onPokeClick(PokemonModel()) })

            ) {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .padding(16.dp)
                ){

                    Column() {
                        Text(
                            text = pokemon.name?.capitalize().toString(),
                            fontWeight = FontWeight.Bold,
                            color = GetBackgroundColor().getColor(type),
                            style = MaterialTheme.typography.h6
                        )
                        Providers(AmbientContentAlpha provides ContentAlpha.high) {

                            pokemon.types!!.forEach { type ->
                                Text(text = type.type.name, style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }

            }
        }
    }


}
