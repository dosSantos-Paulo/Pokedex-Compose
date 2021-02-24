package com.devdossantos.pokedexcompose

import android.app.Application
import com.devdossantos.pokedexcompose.di.apiModule
import com.devdossantos.pokedexcompose.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(apiModule, roomModule))
        }
    }
}