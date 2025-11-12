package com.example.od2characterapp.data.local

import androidx.room.TypeConverter
import com.example.od2characterapp.domain.model.RacaType
import com.example.od2characterapp.domain.model.ClasseType

class Converters {
    @TypeConverter
    fun fromRaca(value: RacaType?) = value?.name

    @TypeConverter
    fun toRaca(value: String?) = value?.let { RacaType.valueOf(it) }

    @TypeConverter
    fun fromClasse(value: ClasseType?) = value?.name

    @TypeConverter
    fun toClasse(value: String?) = value?.let { ClasseType.valueOf(it) }
}
