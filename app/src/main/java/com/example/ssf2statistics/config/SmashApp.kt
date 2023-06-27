package com.example.ssf2statistics.config

import android.app.Application
import androidx.room.Room
import com.example.ssf2statistics.dao.PersonajeDao

class SmashApp: Application() {

    companion object{
        lateinit var db:AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "SFlash").build()
    }

}