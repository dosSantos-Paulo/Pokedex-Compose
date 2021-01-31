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
import androidx.lifecycle.ViewModelProvider
import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.database.AppDataBase
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity
import com.devdossantos.pokedexcompose.database.repository.DataBaseRepository
import com.devdossantos.pokedexcompose.database.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.utils.GetBackgroundColor
import com.devdossantos.pokedexcompose.utils.loadPicture
import com.devdossantos.pokedexcompose.view.ui.theme.PokedexComposeTheme
import com.devdossantos.pokedexcompose.view.ui.theme.SharedItens.Companion.getPokemon

class DetailActivity : AppCompatActivity() {
    private val _pokemon = getPokemon()
    private val _dbList = mutableListOf<PokemonEntity>()
    private lateinit var _dbViewModel: DataBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _dbViewModel = ViewModelProvider(
            this,
            DataBaseViewModel.DataBaseViewModelFactory(
                DataBaseRepository(AppDataBase.getDatabase(this).baseDao())
            )
        ).get(DataBaseViewModel::class.java)

        _dbViewModel.getAllPokemons().observe(this){
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
                    PokemonRow(_pokemon!!, validator)
                }
            }
        }

    }

    @Composable
    private fun PokemonRow(pokemon: PokemonModel, validator: Boolean) {
        var remember = remember { mutableStateOf(false) }
        remember.value = validator

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
                                    when (val bool = changeFavoriteStatus(remember.value)){
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
        var bool:Boolean? = null
        if (!isFavorite) {

            _dbViewModel.addPokemon(getEntity()).observe(this){
                if (it) {
                    bool = true
                    Toast.makeText(this, "Favoritou", Toast.LENGTH_LONG).show()
                } else {
                    bool = false
                    Toast.makeText(this, "Não foi possivel armazenar", Toast.LENGTH_LONG).show()
                }

            }

        } else if (isFavorite) {
            _dbViewModel.deletePokemon(_pokemon!!.id!!.toInt()).observe(this){
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
}
