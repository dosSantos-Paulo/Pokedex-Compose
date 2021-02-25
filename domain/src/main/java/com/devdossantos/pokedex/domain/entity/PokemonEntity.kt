package com.devdossantos.pokedex.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val sprites: String,
    @ColumnInfo
    val types1: String,
    @ColumnInfo
    val types2: String
)