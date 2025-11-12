package com.example.od2characterapp.data.local.entity

import androidx.room.ColumnInfo

data class AtributosEntity(
    @ColumnInfo(name = "forca") val forca: Int,
    @ColumnInfo(name = "destreza") val destreza: Int,
    @ColumnInfo(name = "constituicao") val constituicao: Int,
    @ColumnInfo(name = "inteligencia") val inteligencia: Int,
    @ColumnInfo(name = "sabedoria") val sabedoria: Int,
    @ColumnInfo(name = "carisma") val carisma: Int
)
