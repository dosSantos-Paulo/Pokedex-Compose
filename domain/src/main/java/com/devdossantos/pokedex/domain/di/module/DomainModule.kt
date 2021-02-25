package com.devdossantos.pokedex.domain.di.module

import com.devdossantos.pokedex.domain.api.usecase.GetPokemonUseCase
import com.devdossantos.pokedex.domain.api.usecase.GetPokemonUseCaseImp
import org.koin.dsl.module

val domainModule = module {
    single {GetPokemonUseCaseImp(get()) as GetPokemonUseCase }
}