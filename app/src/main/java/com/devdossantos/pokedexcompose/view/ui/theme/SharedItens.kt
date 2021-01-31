package com.devdossantos.pokedexcompose.view.ui.theme

import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity

class SharedItens {


    companion object {
        private var pokemon: PokemonEntity? = null

        fun setPokemon(pk: PokemonEntity) {
            pokemon = pk
        }

        fun getPokemon(): PokemonEntity? {
            return pokemon
        }
    }
}