package com.devdossantos.pokedex.domain.di.module

import com.devdossantos.pokedex.domain.error.ErrorHandlerImp
import com.devdossantos.pokedex.domain.usecase.GetPokemonUseCase
import com.devdossantos.pokedex.domain.usecase.GetPokemonUseCaseImp
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCase
import com.devdossantos.pokedex.domain.usecase.GetDataBaseUseCaseImp
import org.koin.dsl.module

val useCaseModules = module {
    single { GetPokemonUseCaseImp(get(), get()) as GetPokemonUseCase }
    single { GetDataBaseUseCaseImp(get(), get()) as GetDataBaseUseCase }
}

val errorHandlerModules = module {
    single { ErrorHandlerImp() }
}