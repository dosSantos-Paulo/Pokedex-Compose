package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedexcompose.api.repository.KtorRepository
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        KtorRepository()
    }

    viewModel{
        PokeViewModel(
             _repository = get()
        )
    }
}