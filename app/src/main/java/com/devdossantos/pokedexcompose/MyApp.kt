package com.devdossantos.pokedexcompose

import android.app.Application
import com.devdossantos.pokedex.data.di.apiModule
import com.devdossantos.pokedex.data.di.roomModule
import com.devdossantos.pokedex.domain.di.module.errorHandlerModules
import com.devdossantos.pokedex.domain.di.module.useCaseModules
import com.devdossantos.pokedexcompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MyApp)
            modules(listOf(
                useCaseModules,
                errorHandlerModules,
                apiModule,
                roomModule,
                viewModelModule
            ))
        }

    }
}