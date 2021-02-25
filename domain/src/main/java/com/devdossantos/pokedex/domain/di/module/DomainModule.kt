package com.devdossantos.pokedex.domain.di.module

import com.devdossantos.pokedex.domain.api.usecase.GetPokemonUseCase
import com.devdossantos.pokedex.domain.api.usecase.GetPokemonUseCaseImp
import com.devdossantos.pokedex.domain.database.usecase.GetDataBaseUseCase
import com.devdossantos.pokedex.domain.database.usecase.GetDataBaseUseCaseImp
import org.koin.dsl.module

val domainModule = module {
    single { GetPokemonUseCaseImp(get()) as GetPokemonUseCase }
    single { GetDataBaseUseCaseImp(get()) as GetDataBaseUseCase }
}