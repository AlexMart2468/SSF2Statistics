package com.example.ssf2statistics.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje

class PersonajeRepository constructor(private val personajeDao: PersonajeDao) {
    fun listarpersonajes(): LiveData<List<Personaje>> = personajeDao.getAllPersonajes().asLiveData()

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
