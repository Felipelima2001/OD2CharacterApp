package com.example.od2characterapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.od2characterapp.data.local.dao.PersonagemDao
import com.example.od2characterapp.data.local.entity.PersonagemEntity

// Aumente a versão para 2
@Database(
    entities = [PersonagemEntity::class],
    version = 3,
    exportSchema = false // Geralmente usado como false em desenvolvimento
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personagemDao(): PersonagemDao
}