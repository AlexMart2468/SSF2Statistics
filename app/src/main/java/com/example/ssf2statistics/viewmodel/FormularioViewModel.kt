package com.example.ssf2statistics.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ssf2statistics.config.Constantes
import com.example.ssf2statistics.config.SmashApp.Companion.db
import com.example.ssf2statistics.models.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.text.*

class FormularioViewModel: ViewModel() {
    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var fismash = MutableLiveData<String>()
    var titler = MutableLiveData<String>()
    var entrysm = MutableLiveData<String>()
    var origen = MutableLiveData<String>()
    var operacion = Constantes.OPERACION_INSERTAR
    val thisgamewinneris = MutableLiveData<Boolean>()

    fun insertPersonaje(){
        if(validarinformacion()){
            var mPersonaje = Personaje(0, nombre.value!!,fismash.value!!, titler.value!!, entrysm.value!!, origen.value!!)
            when(operacion){
                Constantes.OPERACION_INSERTAR->{
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO){
                            db.personajeDao().insertPersonaje(
                                arrayListOf<Personaje>(
                                    mPersonaje
                                )
                            )
                        }
                        thisgamewinneris.value = result != null

                    }
                }
                Constantes.OPERACION_EDITAR->{
                    mPersonaje.id = id.value!!
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO){
                            db.personajeDao().updatePersonaje(mPersonaje)
                        }

                        thisgamewinneris.value = (result != null )
                    }
                }
            }
        }else{
            thisgamewinneris.value = false
        }


    }

    fun cargarDatos() {
        viewModelScope.launch {
            var personaje = withContext(Dispatchers.IO){
                db.personajeDao().getById(id.value!!)
            }

            nombre.value = personaje.nombre
            fismash.value = personaje.finalsmash
            titler.value = personaje.resume
            entrysm.value = personaje.juego
            origen.value = personaje.saga
        }
    }

    private fun validarinformacion():Boolean{
        return !(nombre.value.isNullOrEmpty() ||
                fismash.value.isNullOrEmpty() ||
                titler.value.isNullOrEmpty() ||
                entrysm.value.isNullOrEmpty() ||
                origen.value.isNullOrEmpty()!!
                )
    }

    fun deletePersonaje() {
        var mPersonaje = Personaje(id.value!!, "","","","","")
        viewModelScope.launch{
            val result = withContext(Dispatchers.IO){
                db.personajeDao().deletePersonaje(mPersonaje)
            }
            thisgamewinneris.value = result != null
        }
    }

}
