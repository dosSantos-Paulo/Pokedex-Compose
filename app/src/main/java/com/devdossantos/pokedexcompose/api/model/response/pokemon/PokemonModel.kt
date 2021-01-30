package com.devdossantos.pokedexcompose.api.model.response.pokemon

import com.devdossantos.pokedexcompose.api.model.response.ability.AbilityModel
import com.devdossantos.pokedexcompose.api.model.response.sprite.SpriteModel
import com.devdossantos.pokedexcompose.api.model.response.stats.StatsModel
import com.devdossantos.pokedexcompose.api.model.response.type.TypeModel

data class PokemonModel(
    val abilities: List<AbilityModel>? = null,
    val height: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val sprites: SpriteModel? = null,
    val stats: List<StatsModel>? = null,
    val types: List<TypeModel>? = null,
    val weight: Int? = null
)