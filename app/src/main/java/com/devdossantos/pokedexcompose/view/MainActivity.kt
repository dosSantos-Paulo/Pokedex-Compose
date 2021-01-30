package com.devdossantos.pokedexcompose.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalFoundationApi
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

        LazyVerticalGrid(
            cells = GridCells.Adaptive(220.dp),
            content = {
                items(items = pokemonList) { pokemon ->
                    PokemonRow(pokemon = pokemon, onPokeClick = {

                    })
                }
            })


    }

    @Composable
    private fun PokemonRow(pokemon: PokemonModel, onPokeClick: (PokemonModel) -> Unit) {
        var remember = remember { mutableStateOf(false) }
        var favoriteColor = remember { mutableStateOf(Color.Transparent) }
        var bitmap by remember { mutableStateOf<Bitmap?>(null) }

        Glide.with(ContextAmbient.current).asBitmap()
            .load(pokemon.sprites?.front_default)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    bitmap = resource
                }
            })

        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {

            val type = pokemon.types?.get(0)?.type?.name.toString()


            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .fillMaxWidth()
                    .clickable(onClick = {
                        favoriteColor.value =
                            favoriteMessage(remember.value, pokemon.name.toString())
                    })

            ) {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .padding(16.dp)
                ) {

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

                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(Color.Transparent)
                                .align(Alignment.BottomEnd),
                            alignment = Alignment.BottomEnd,
                            contentScale = ContentScale.Fit,
                            alpha = 1f,
                            colorFilter = null
                        )
                    }
//                    Image(
//                        bitmap = it.asImageBitmap(),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(120.dp)
//                            .background(Color.Transparent)
//                            .align(Alignment.BottomEnd),
//                        alignment = Alignment.BottomEnd,
//                        contentScale = ContentScale.Fit,
//                        alpha = 1f,
//                        colorFilter = null
//                    )

                }

            }
        }
    }


    private fun favoriteMessage(it: Boolean, name: String): Color {
        var color = Color.Transparent

        if (it) {
            Toast.makeText(this, "Você desfavoriou o $name", Toast.LENGTH_LONG).show()
            color = Color.Transparent
        } else if (!it) {
            Toast.makeText(this, "Você favoriou o $name", Toast.LENGTH_LONG).show()
            color = Color.Yellow
        }

        return color
    }


}
