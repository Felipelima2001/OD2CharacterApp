package com.example.od2characterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded // IMPORTANTE: Anotação para embutir a AtributosEntity

@Entity(tableName = "personagens")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val raca: String,
    val classe: String,

    // Inclui todos os campos de AtributosEntity nesta tabela
    @Embedded
    val atributos: AtributosEntity
)