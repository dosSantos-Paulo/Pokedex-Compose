package com.devdossantos.pokedex.domain.di.module

import com.devdossantos.pokedex.domain.usecase.GetPokemonUseCase
import com.devdossantos.pokedex.domain.usecase.GetPokemonUseCaseImp
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCase
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCaseImp
import org.koin.dsl.module

val domainModule = module {
    single { GetPokemonUseCaseImp(get()) as GetPokemonUseCase }
    single { GetDataBaseUseCaseImp(get()) as GetDataBaseUseCase }
}