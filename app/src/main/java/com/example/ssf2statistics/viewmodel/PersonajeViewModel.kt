package com.example.ssf2statistics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssf2statistics.models.Personaje
import com.example.ssf2statistics.repository.PersonajeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeViewModel(private val repository: PersonajeRepository) : ViewModel() {
    val allPersonajes: LiveData<List<Personaje>> = repository.allPersonajes

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