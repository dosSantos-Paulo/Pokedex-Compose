package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedexcompose.api.repository.PokeRepository
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        PokeRepository()
    }

    viewModel{
        PokeViewModel(
            _repository = get()
        )
    }
}