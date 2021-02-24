package com.devdossantos.pokedex.data.di

import com.devdossantos.pokedex.data.api.repository.KtorRepository
import org.koin.dsl.module

val apiModule = module {
    factory { KtorRepository() }
}