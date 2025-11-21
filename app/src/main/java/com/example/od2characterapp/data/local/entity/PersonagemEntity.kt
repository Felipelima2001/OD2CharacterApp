package com.example.od2characterapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

@Entity(tableName = "personagens")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val raca: String,
    val classe: String,


    @Embedded
    val atributos: AtributosEntity,

    val currentHp: Int = 20
)
