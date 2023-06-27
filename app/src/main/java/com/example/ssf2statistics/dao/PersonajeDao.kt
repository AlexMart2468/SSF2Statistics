package com.example.ssf2statistics.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ssf2statistics.models.Personaje
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonajeDao {
    @Query("SELECT * FROM personajes")
    fun getAllPersonajes(): Flow<List<Personaje>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonaje(personaje: Personaje)

    @Update
    suspend fun updatePersonaje(personaje: Personaje)

    @Delete
    suspend fun deletePersonaje(personaje: Personaje)
}