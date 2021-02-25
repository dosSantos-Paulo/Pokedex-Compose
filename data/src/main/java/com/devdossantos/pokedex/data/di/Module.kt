package com.devdossantos.pokedex.data.di

import com.devdossantos.pokedex.data.api.repository.GetPokemonImp
import com.devdossantos.pokedex.domain.api.repository.GetPokemonRepository
import org.koin.dsl.module

val apiModule = module {
    single { GetPokemonImp() as GetPokemonRepository }
}