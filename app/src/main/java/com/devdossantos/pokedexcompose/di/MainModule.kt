package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedex.data.database.AppDataBase
import com.devdossantos.pokedex.data.database.repository.DataBaseRepository
import com.devdossantos.pokedexcompose.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokeViewModel(get()) }
}

val roomModule = module {
    single { AppDataBase.getDatabase(get()).baseDao() }
    single { DataBaseRepository(get()) }
    viewModel { DataBaseViewModel(get()) }
}