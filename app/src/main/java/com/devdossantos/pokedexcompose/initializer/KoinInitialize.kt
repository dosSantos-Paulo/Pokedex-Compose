package com.devdossantos.pokedexcompose.initializer

import android.content.Context
import androidx.startup.Initializer
import com.devdossantos.pokedex.data.di.apiModule
import com.devdossantos.pokedex.data.di.roomModule
import com.devdossantos.pokedex.domain.di.module.errorHandlerModules
import com.devdossantos.pokedex.domain.di.module.useCaseModules
import com.devdossantos.pokedexcompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitialize : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        return startKoin{
            androidContext(context)
            modules(listOf(
                useCaseModules,
                errorHandlerModules,
                apiModule,
                roomModule,
                viewModelModule))
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}