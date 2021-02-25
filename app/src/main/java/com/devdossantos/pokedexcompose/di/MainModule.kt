package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedexcompose.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokeViewModel(get()) }
    viewModel { DataBaseViewModel(get()) }
}