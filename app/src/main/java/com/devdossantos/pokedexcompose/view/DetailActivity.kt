package com.devdossantos.pokedexcompose.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.database.AppDataBase
import com.devdossantos.pokedexcompose.database.dao.BaseDao
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.database.repository.DataBaseRepository
import com.devdossantos.pokedexcompose.database.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.utils.loadPicture
import com.devdossantos.pokedexcompose.view.ui.theme.PokedexComposeTheme
import com.devdossantos.pokedexcompose.view.ui.theme.SharedItens.Companion.getPokemon

class DetailActivity : AppCompatActivity() {
    private val pokemon = getPokemon()

    private val databaseViewModel = ViewModelProvider(
        this,
        DataBaseViewModel.DataBaseViewModelFactory(
            DataBaseRepository(
                AppDataBase.getDatabase(this).baseDao()
            )
        )
    ).get(DataBaseViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PokemonRow(pokemon!!)
                }
            }
        }
    }

    @Composable
    private fun PokemonRow(pokemon: PokemonModel) {
        var remember = remember { mutableStateOf(false) }

        var color = Color.LightGray
        if (remember.value) {
            color = Color.LightGray
        } else if (!remember.value) {
            color = Color.Yellow
        }


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
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                    .border(
                        width = 1.dp,
                        color = GetBackgroundColor().getColor(type),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                ) {

                    loadPicture(
                        url = pokemon.sprites!!.front_default
                    ).value?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            modifier = Modifier
                                .height(250.dp)
                                .width(250.dp)
                                .background(Color.Transparent)
                                .align(Alignment.BottomEnd),
                            alignment = Alignment.BottomEnd,
                            contentScale = ContentScale.Fit,
                            alpha = 1f,
                            colorFilter = null
                        )
                    }

                    loadPicture(
                        url = "https://pngimg.com/uploads/star/star_PNG41515.png"
                    ).value?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            modifier = Modifier
                                .padding(16.dp)
                                .height(40.dp)
                                .background(Color.Transparent)
                                .align(Alignment.TopEnd)
                                .clickable(onClick = {
                                    remember.value = changeFavoriteStatus(pokemon, remember.value)
                                }),
                            alignment = Alignment.TopEnd,
                            contentScale = ContentScale.Fit,
                            alpha = 1f,
                            colorFilter = ColorFilter(color, BlendMode.SrcIn)
                        )
                    }

                }

            }
        }
    }

    private fun changeFavoriteStatus(pokemon: PokemonModel, isFavorite: Boolean): Boolean {
        val tipo : MutableList<String> = mutableListOf()

        pokemon.types!!.forEach {
            tipo.add(it.type.toString())
        }

        val newPokemon = PokemonEntity(
            pokemon.id!!.toInt(),
            pokemon.name.toString(),
            pokemon.sprites!!.front_default,
            tipo
        )

        var bool = false
        if (isFavorite) {
            databaseViewModel.deletePokemon(pokemon.id).observe(this){
                Toast.makeText(this, "Excluido", Toast.LENGTH_LONG).show()
            }
            bool = false
        } else if (!isFavorite) {
            databaseViewModel.addPokemon(newPokemon).observe(this) {
                Toast.makeText(this, "Armazenado", Toast.LENGTH_LONG).show()
            }
            bool = true
        }
        return bool
    }
}
