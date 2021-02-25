package com.devdossantos.pokedexcompose.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.devdossantos.pokedex.domain.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.utils.loadPicture
import com.devdossantos.pokedexcompose.view.ui.theme.PokedexComposeTheme
import com.devdossantos.pokedexcompose.view.ui.theme.SharedItens.Companion.getPokemon
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val _pokemon = getPokemon()
    private val _dbList = mutableListOf<PokemonEntity>()
    private val _dbViewModel: DataBaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _dbViewModel.getAllPokemons().observe(this) {
            _dbList.addAll(it)
            init()
        }

    }

    private fun init() {
        var validator = false
        _dbList.forEach {
            if (it.id == _pokemon!!.id) {
                validator = true
            }
        }

        setContent {
            PokedexComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    ExtendedFAB()
                    PokemonRow(_pokemon!!, validator)
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
    private fun PokemonRow(pokemon: PokemonEntity, validator: Boolean) {
        var remember = remember { mutableStateOf(false) }
        remember.value = validator

        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 6.dp,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                    .border(
                        width = 1.dp,
                        color = GetBackgroundColor().getColor(_pokemon!!.types1),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()

                ) {


                    Column() {
                        Text(
                            text = pokemon.name?.capitalize().toString(),
                            fontWeight = FontWeight.Bold,
                            color = GetBackgroundColor().getColor(_pokemon.types1),
                            style = MaterialTheme.typography.h4
                        )
                        Providers(AmbientContentAlpha provides ContentAlpha.high) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 50.dp, top = 100.dp)
                                    .border(
                                        width = 1.dp,
                                        color = GetBackgroundColor().getColor(_pokemon.types1),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    text = _pokemon.types1,
                                    style = MaterialTheme.typography.body2
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 50.dp, top = 4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = GetBackgroundColor().getColor(_pokemon.types2),
                                        shape = RoundedCornerShape(6.dp)
                                    )

                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    text = _pokemon.types2,
                                    style = MaterialTheme.typography.body2
                                )
                            }
                            Box(
                                modifier = Modifier

                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 50.dp, top = 4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    text = "id: ${_pokemon.id}",
                                    style = MaterialTheme.typography.body2
                                )
                            }


                        }
                    }


                    loadPicture(
                        url = _pokemon.sprites!!
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
                                    when (val bool = changeFavoriteStatus(remember.value)) {
                                        true, false -> remember.value = bool
                                        null -> remember.value = remember.value
                                    }
                                }),
                            alignment = Alignment.TopEnd,
                            contentScale = ContentScale.Fit,
                            alpha = 1f,
                            colorFilter = ColorFilter(getStarColor(remember.value), BlendMode.SrcIn)
                        )
                    }


                }

            }
        }
    }

    private fun getStarColor(isFavorite: Boolean): Color {
        var color = Color.White
        if (isFavorite) {
            color = Color.Yellow
        } else if (!isFavorite) {
            color = Color.LightGray
        }
        return color
    }

    private fun changeFavoriteStatus(isFavorite: Boolean): Boolean? {
        var bool: Boolean? = null
        if (!isFavorite) {

            _dbViewModel.addPokemon(getEntity()).observe(this) {
                if (it) {
                    bool = true
                    Toast.makeText(this, "Favoritou", Toast.LENGTH_LONG).show()
                } else {
                    bool = false
                    Toast.makeText(this, "Não foi possivel armazenar", Toast.LENGTH_LONG).show()
                }

            }

        } else if (isFavorite) {
            _dbViewModel.deletePokemon(_pokemon!!.id!!.toInt()).observe(this) {
                if (it) {
                    bool = false
                    Toast.makeText(this, "Excluiu com sucesso", Toast.LENGTH_LONG).show()
                } else {
                    bool = true
                    Toast.makeText(this, "Não foi possivel excluir", Toast.LENGTH_LONG).show()
                }

            }
        }

        return bool
    }

    private fun getEntity(): PokemonEntity {

        return PokemonEntity(
            _pokemon!!.id!!.toInt(),
            _pokemon!!.name!!.toString(),
            _pokemon!!.sprites!!,
            _pokemon!!.types1,
            _pokemon!!.types2
        )
    }
}
