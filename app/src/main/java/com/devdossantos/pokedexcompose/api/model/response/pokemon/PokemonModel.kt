package com.devdossantos.pokedexcompose.api.model.response.pokemon

import com.devdossantos.pokedexcompose.api.model.response.ability.AbilityModel
import com.devdossantos.pokedexcompose.api.model.response.sprite.SpriteModel
import com.devdossantos.pokedexcompose.api.model.response.stats.StatsModel
import com.devdossantos.pokedexcompose.api.model.response.type.TypeModel

data class PokemonModel(
    val abilities: List<AbilityModel>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: SpriteModel,
    val stats: List<StatsModel>,
    val types: List<TypeModel>,
    val weight: Int
)