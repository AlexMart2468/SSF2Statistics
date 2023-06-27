package com.example.ssf2statistics.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personajes")
data class Personaje(
    @PrimaryKey val id: Int,
    val nombre: String,
    val finalsmash: String,
    val resume: String,
    val juego: String,
    val saga: String,
)
