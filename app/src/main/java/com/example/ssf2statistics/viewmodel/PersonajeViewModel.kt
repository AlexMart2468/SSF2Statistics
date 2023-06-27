package com.example.ssf2statistics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssf2statistics.config.AppDatabase
import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje
import com.example.ssf2statistics.repository.PersonajeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeViewModel(application: Application) : AndroidViewModel(application)  {
    val ListaNotas: LiveData<List<Personaje>>
    val repository: PersonajeRepository
    init{
        val PersonajeDao = AppDatabase.getDatabase(application).personajeDao()
        repository = PersonajeRepository(PersonajeDao)
        ListaNotas = repository.listarpersonajes()
    }

    fun insertPersonaje(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPersonaje(personaje)
        }
    }

    fun updatePersonaje(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePersonaje(personaje)
        }
    }

    fun deletePersonaje(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePersonaje(personaje)
        }
    }
}