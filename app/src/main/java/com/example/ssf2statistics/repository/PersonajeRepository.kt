package com.example.ssf2statistics.repository

import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje

class PersonajeRepository(private val personajeDao: PersonajeDao) {

    suspend fun getAllPersonajes(): List<Personaje> {
        return personajeDao.getAllPersonajes()
    }
}

