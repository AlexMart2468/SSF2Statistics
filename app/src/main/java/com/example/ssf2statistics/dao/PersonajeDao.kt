package com.example.ssf2statistics.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ssf2statistics.models.Personaje

@Dao
interface PersonajeDao {
    @Query("SELECT * FROM personajes")
    fun getAllPersonajes(): LiveData<List<Personaje>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonaje(personaje: Personaje)

    @Update
    suspend fun updatePersonaje(personaje: Personaje)

    @Delete
    suspend fun deletePersonaje(personaje: Personaje)
}