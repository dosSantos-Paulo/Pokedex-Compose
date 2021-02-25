package com.devdossantos.pokedex.data.di

import com.devdossantos.pokedex.data.api.repository.GetPokemonImp
import com.devdossantos.pokedex.data.database.AppDataBase
import com.devdossantos.pokedex.data.database.repository.DataBaseRepositoryImp
import com.devdossantos.pokedex.domain.api.repository.GetPokemonRepository
import com.devdossantos.pokedex.domain.database.repository.DataBaseRepository
import org.koin.dsl.module

val apiModule = module {
    single { GetPokemonImp() as GetPokemonRepository }
}

val roomModule = module {
    single { AppDataBase.getDatabase(get()).baseDao() }
    single { DataBaseRepositoryImp(get()) as DataBaseRepository}
}