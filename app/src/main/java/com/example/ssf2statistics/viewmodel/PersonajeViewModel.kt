package com.example.ssf2statistics.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ssf2statistics.config.AppDatabase
import com.example.ssf2statistics.config.SmashApp.Companion.db
import com.example.ssf2statistics.models.Personaje
import com.example.ssf2statistics.repository.PersonajeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonajeViewModel(application: Application) : AndroidViewModel(application)  {
    private val _listaNotas = MutableLiveData<List<Personaje>?>()
    val listaNotas: MutableLiveData<List<Personaje>?> get() = _listaNotas
    var parametroBusqueda = MutableLiveData<String>()

    private val repository: PersonajeRepository

    init {
        val personajeDao = AppDatabase.getDatabase(application).personajeDao()
        repository = PersonajeRepository(personajeDao)
        listarPersonajes()
    }

     fun listarPersonajes(){
        viewModelScope.launch {
            _listaNotas.value = withContext(Dispatchers.IO){


                db.personajeDao().getAllPersonajes()
            }
        }
    }

    fun buscarPersonaje() {
        viewModelScope.launch {
            listaNotas.value = withContext(Dispatchers.IO){
                db.personajeDao().getByName(parametroBusqueda.value!!)
            }
        }
    }


}