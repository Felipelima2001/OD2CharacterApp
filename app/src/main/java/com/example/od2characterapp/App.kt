package com.example.od2characterapp

import android.app.Application
import androidx.room.Room
import com.example.od2characterapp.data.local.AppDatabase

class App : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "od2-db")
            // CORREÇÃO: Força o Room a destruir e recriar o banco de dados se a versão mudar.
            .fallbackToDestructiveMigration()
            .build()
    }
}