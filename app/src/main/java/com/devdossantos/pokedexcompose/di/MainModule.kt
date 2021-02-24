package com.devdossantos.pokedexcompose.di


import com.devdossantos.pokedexcompose.api.repository.KtorRepository
import com.devdossantos.pokedexcompose.database.AppDataBase
import com.devdossantos.pokedexcompose.database.repository.DataBaseRepository
import com.devdossantos.pokedexcompose.database.viewmodel.DataBaseViewModel
import com.devdossantos.pokedexcompose.viewmodel.PokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    factory { KtorRepository() }
    viewModel { PokeViewModel(get()) }
}

val roomModule = module {
    single { AppDataBase.getDatabase(get()).baseDao() }
    single { DataBaseRepository(get()) }
    viewModel { DataBaseViewModel(get()) }
}