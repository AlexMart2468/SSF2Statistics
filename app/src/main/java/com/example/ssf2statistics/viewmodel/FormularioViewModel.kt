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
import java.util.OptionalLong
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
                            /*db.personajeDao().insertPersonaje(arrayListOf(
                                Personaje(1,"Mario","Mario Finale","El fontanero mas famoso","Super Smash Bros. 64","Super Mario Bros"),
                                Personaje(2,"Donkey Kong","Jungle Rush","El rival original","Super Smash Bros. 64","Donkey Kong"),
                                Personaje(3,"Link","Ancient Bow and Arrow","El Heroe de Hyrule","Super Smash Bros. 64","The Legend Of Zelda"),
                                Personaje(4,"Samus","Zero Laser","La Cazarrecompensas espacial","Super Smash Bros. 64","Metroid"),
                                Personaje(5,"Yoshi","Stampede!","El dinosaurio amigo","Super Smash Bros. 64","Yoshi Island"),
                                Personaje(6,"Kirby","Ultra Sword","La adorable bola rosa","Super Smash Bros. 64","Kirby"),
                                Personaje(7,"Fox","Team Star Fox","El lider","Super Smash Bros. 64","Starfox"),
                                Personaje(8,"Pikachu","Volt Tackle","El Pokemon electrico","Super Smash Bros. 64","Pokemon Red & Blue"),
                                Personaje(9,"Luigi","Poltergeist G-00","El inseparable hermano","Super Smash Bros. 64","Super Mario Bros"),
                                Personaje(10,"Captain Falcon","Blue Falcon","El piloto mas famoso","Super Smash Bros. 64","F-Zero"),
                                Personaje(11,"Ness","PK Starstorm","Un chico con poderes","Super Smash Bros. 64","EarthBound"),
                                Personaje(12,"Jigglypuff","Puff Up","Uso Canto","Super Smash Bros. 64","Pokemon Red & Blue"),
                            ))*/
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
        var mPersonaje = id.value ?: return
        viewModelScope.launch{
            val personaje = withContext(Dispatchers.IO){
                db.personajeDao().getById(mPersonaje)
            }
            personaje?.let {
                val result = withContext(Dispatchers.IO){
                    db.personajeDao().deletePersonaje(it)
                }
                thisgamewinneris.value = result != null
            }


        }
    }

}
