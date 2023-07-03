package com.example.ssf2statistics.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ssf2statistics.dao.PersonajeDao
import com.example.ssf2statistics.models.Personaje

@Database(entities = [Personaje::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personajeDao(): PersonajeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "SFlash"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

