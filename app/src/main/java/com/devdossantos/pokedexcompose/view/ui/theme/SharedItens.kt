package com.devdossantos.pokedexcompose.view.ui.theme

import com.devdossantos.pokedexcompose.api.model.response.pokemon.PokemonModel

class SharedItens {


    companion object {
        private var pokemon: PokemonModel? = null

        fun setPokemon(pk: PokemonModel) {
            pokemon = pk
        }

        fun getPokemon(): PokemonModel? {
            return pokemon
        }
    }
}