package com.devdossantos.pokedex.domain.model.pokemon

import com.devdossantos.pokedex.domain.model.ability.AbilityModel
import com.devdossantos.pokedex.domain.model.sprite.SpriteModel
import com.devdossantos.pokedex.domain.model.stats.StatsModel
import com.devdossantos.pokedex.domain.model.type.TypeModel

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