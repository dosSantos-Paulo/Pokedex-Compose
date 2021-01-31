package com.devdossantos.pokedexcompose.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
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
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.utils.loadPicture
import com.devdossantos.pokedexcompose.view.ui.theme.SharedItens.Companion.setPokemon
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private val _pokeViewModel: PokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            if (isConnected(this)) {
                ExtendedFAB()
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(140.dp)
                )
                getList()
            } else {
                AlternaTiveWay()
            }

        }



    }

    private fun getList() {
        _pokeViewModel.getList().observe(this) {

            setContent {
                MaterialTheme(
                    colors = lightColors()
                ) {
                    PokeList(it)
                    ExtendedFAB()
                }
            }
        }
    }

    @Composable
    private fun AlternaTiveWay() {
        Toast.makeText(this, "Sem conexção, apenas seus favoritos estão ativos", Toast.LENGTH_LONG).show()

        loadPicture(
            url = "https://www.pngkey.com/png/full/3-35940_pikachu-is-hardcore-imagenes-para-banner-png.png"
        ).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier
                    .size(120.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                alpha = 1f,
                colorFilter = null
            )
        }

        loadPicture(
            url = "https://cdn.bulbagarden.net/upload/4/4b/Pok%C3%A9dex_logo.png"
        ).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier
                    .size(50.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                alpha = 1f,
                colorFilter = null
            )
        }

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
                    text = { Text(text = "FAVORITOS") },
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Transparent),
                    onClick = {
                        ToFav()

                    })
            }
        ) {

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
                    text = { Text(text = "FAVORITOS") },
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Transparent),
                    onClick = {
                        ToFav()

                    })
            }
        ) {

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
                elevation = 6.dp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                    .border(
                        width = 1.dp,
                        color = GetBackgroundColor().getColor(type),
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

    private fun getEntity(_pokemon: PokemonModel): PokemonEntity {
        val newPokemon: PokemonEntity
        val types = mutableListOf<String>()
        _pokemon!!.types!!.forEach {
            types.add(it.toString())
        }

        if (types.size == 1) {
            newPokemon = PokemonEntity(
                _pokemon!!.id!!.toInt(),
                _pokemon!!.name!!.toString(),
                _pokemon!!.sprites!!.front_default,
                _pokemon!!.types!![0].type.name,
                ""
            )
        } else {
            newPokemon = PokemonEntity(
                _pokemon!!.id!!.toInt(),
                _pokemon!!.name!!.toString(),
                _pokemon!!.sprites!!.front_default,
                _pokemon!!.types!![0].type.name,
                _pokemon!!.types!![1].type.name
            )
        }

        return newPokemon
    }

    private fun toDetail(pokemon: PokemonModel) {
        val intent = Intent(this, DetailActivity::class.java)
        setPokemon(getEntity(pokemon))
        startActivity(intent)
    }

    private fun ToFav() {
        val intent = Intent(this, FavActivity::class.java)
        startActivity(intent)
    }


    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val ni = cm.activeNetworkInfo
            return ni != null && ni.isConnected
        }
        return false
    }

}
