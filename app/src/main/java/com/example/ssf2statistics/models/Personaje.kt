package com.example.ssf2statistics.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personajes")
data class Personaje(
    @PrimaryKey (autoGenerate = true)
    var id: Long = 0,
    val nombre: String,
    val finalsmash: String,
    val resume: String,
    val juego: String,
    val saga: String,
)
