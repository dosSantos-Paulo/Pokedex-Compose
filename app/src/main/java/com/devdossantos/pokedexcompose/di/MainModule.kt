package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedexcompose.ui.favorite.DataBaseViewModel
import com.devdossantos.pokedexcompose.ui.main.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokeViewModel(get()) }
    viewModel { DataBaseViewModel(get()) }
}