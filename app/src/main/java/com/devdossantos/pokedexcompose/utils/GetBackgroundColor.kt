package com.devdossantos.pokedexcompose.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.devdossantos.pokedexcompose.R

class GetBackgroundColor {
    fun getColor(type: String): Color {
        var color = Color.White
        when (type) {
            "normal" -> color = Color.LightGray
            "fighting" -> color = Color.Gray
            "flying" -> color = Color.LightGray
            "poison" -> color = Color.Gray
            "ground" -> color = Color.LightGray
            "rock" -> color = Color.DarkGray
            "bug" -> color = Color.Cyan
            "ghost" -> color = Color.Gray
            "steel" -> color = Color.Gray
            "fire" -> color = Color.Red
            "water" -> color = Color.Blue
            "grass" -> color = Color.Green
            "electric" -> color = Color.Yellow
            "psychic" -> color = Color.Cyan
            "ice" -> color = Color.Blue
            "dragon" -> color = Color.LightGray
            "dark" -> color = Color.DarkGray
            "fairy" -> color = Color.Black
            "unknown" -> color = Color.Gray
            "shadow" -> color = Color.DarkGray
        }
        return color
    }
}

