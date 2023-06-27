package com.example.ssf2statistics.repository

import androidx.lifecycle.LiveData
import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje

class PersonajeRepository(private val personajeDao: PersonajeDao) {
    val allPersonajes: LiveData<List<Personaje>> = personajeDao.getAllPersonajes()

    suspend fun insertPersonaje(personaje: Personaje) {
        personajeDao.insertPersonaje(personaje)
    }

    suspend fun updatePersonaje(personaje: Personaje) {
        personajeDao.updatePersonaje(personaje)
    }

    suspend fun deletePersonaje(personaje: Personaje) {
        personajeDao.deletePersonaje(personaje)
    }
}
