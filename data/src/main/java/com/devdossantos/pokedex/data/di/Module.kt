package com.devdossantos.pokedex.data.di

import com.devdossantos.pokedex.data.api.repository.ApiRepositoryImp
import com.devdossantos.pokedex.data.api.repository.GetPokemonImp
import com.devdossantos.pokedex.data.database.AppDataBase
import com.devdossantos.pokedex.data.database.repository.DataBaseRepositoryImp
import com.devdossantos.pokedex.domain.repository.ApiRepository
import com.devdossantos.pokedex.domain.repository.GetPokemonRepository
import com.devdossantos.pokedex.domain.repository.DataBaseRepository
import org.koin.dsl.module

val apiModule = module {
    single { GetPokemonImp(get()) as GetPokemonRepository }
}

val roomModule = module {
    single { AppDataBase.getDatabase(get()).baseDao() }
    single { DataBaseRepositoryImp(get()) as DataBaseRepository }
    single { ApiRepositoryImp() as ApiRepository }
}