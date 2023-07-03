package com.example.ssf2statistics.dao

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
    suspend fun getAllPersonajes(): List<Personaje>

    @Query("SELECT * FROM personajes WHERE id = :id")
    suspend fun getById(id:Long):Personaje

    @Query("SELECT * FROM personajes WHERE nombre LIKE '%'|| :name || '%' OR saga LIKE '%'|| :name || '%'")
    suspend fun getByName(name:String): List<Personaje>

    @Insert
    suspend fun insertPersonaje(personaje: ArrayList<Personaje>):List<Long>

    @Update
    suspend fun updatePersonaje(personaje: Personaje):Int

    @Delete
    suspend fun deletePersonaje(personaje: Personaje):Int
}