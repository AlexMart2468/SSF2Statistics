package com.example.ssf2statistics.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje

@Database(entities = [Personaje::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao
}
