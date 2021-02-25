package com.devdossantos.pokedexcompose.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.devdossantos.pokedex.domain.api.model.pokemon.PokemonModel
import com.devdossantos.pokedex.domain.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.utils.loadPicture
import com.devdossantos.pokedexcompose.view.ui.theme.SharedItens
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalFoundationApi
class FavActivity : AppCompatActivity() {

    private val _dbViewModel: DataBaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExtendedFAB()
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(140.dp)
            )
            getList()
        }

    }

    private fun getList() {
        _dbViewModel.getAllPokemons().observe(this) {

            setContent {
                MaterialTheme(
                    colors = lightColors()
                ) {
                    PokeList(it as MutableList<PokemonEntity>)
                    ExtendedFAB()
                }
            }
        }
    }

    @Composable
    private fun ExtendedFAB() {

        Scaffold(
            backgroundColor = Color.Transparent,
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    backgroundColor = Color.Gray,
                    contentColor = Color.White,
                    icon = {
                        loadPicture(
                            url = "https://lh3.googleusercontent.com/proxy/uu3X0tP6QilOCFi0t47FKedqXiMDOmmGRjbk87wTXsH2kacxmab-fGheC6bu97GT-TKIVY3m5R7OPlBzvhz1yfI83XM-geR7tBxGP-ncmTMNPm0"
                        ).value?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                modifier = Modifier
                                    .size(30.dp),
                                alignment = Alignment.BottomEnd,
                                contentScale = ContentScale.Fit,
                                alpha = 1f,
                                colorFilter = null
                            )
                        }

                    },
                    text = { Text(text = "VOLTAR") },
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Transparent),
                    onClick = {
                        finish()
                    })
            }
        ) {

        }

    }

    @Composable
    private fun PokeList(pokemonList: MutableList<PokemonEntity>) {

        LazyVerticalGrid(
            cells = GridCells.Adaptive(220.dp),
            content = {
                items(items = pokemonList) { pokemon ->
                    PokemonRow(pokemon = pokemon) {

                    }
                }
            })


    }

    @Composable
    private fun PokemonRow(pokemon: PokemonEntity, onPokeClick: (PokemonModel) -> Unit) {
        var remember = remember { mutableStateOf(false) }
        var favoriteColor = remember { mutableStateOf(Color.Transparent) }
        var bitmap by remember { mutableStateOf<Bitmap?>(null) }

        Glide.with(ContextAmbient.current).asBitmap()
            .load(pokemon.sprites)
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

            val type = listOf(pokemon.types1, pokemon.types2)


            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 6.dp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                    .border(
                        width = 1.dp,
                        color = GetBackgroundColor().getColor(type[0]),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .clickable(onClick = {
                        toDetail(pokemon)
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
                            color = GetBackgroundColor().getColor(pokemon.types1),
                            style = MaterialTheme.typography.h6
                        )
                        Providers(AmbientContentAlpha provides ContentAlpha.high) {
                            Text(text = pokemon.types1, style = MaterialTheme.typography.body2)
                            Text(text = pokemon.types2, style = MaterialTheme.typography.body2)
                        }
                    }

                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            modifier = Modifier
                                .height(120.dp)
                                .width(120.dp)
                                .background(Color.Transparent)
                                .align(Alignment.BottomEnd),
                            alignment = Alignment.BottomEnd,
                            contentScale = ContentScale.Fit,
                            alpha = 1f,
                            colorFilter = null
                        )
                    }

                }

            }
        }
    }

    private fun toDetail(pokemon: PokemonEntity) {
        val intent = Intent(this, DetailActivity::class.java)
        SharedItens.setPokemon(pokemon)
        startActivity(intent)
    }


}