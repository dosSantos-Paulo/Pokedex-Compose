package com.devdossantos.pokedexcompose.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devdossantos.pokedexcompose.api.model.response.sprite.SpriteModel

@Entity(tableName = "pokemon_list")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val sprites: String,
    @ColumnInfo
    val types: List<String>
)