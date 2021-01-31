package com.devdossantos.pokedexcompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devdossantos.pokedexcompose.database.dao.BaseDao
import com.devdossantos.pokedexcompose.database.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase () {
    abstract fun baseDao(): BaseDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "marvel_my_hero"
                ).build()
            }

            return INSTANCE!!

        }
    }
}