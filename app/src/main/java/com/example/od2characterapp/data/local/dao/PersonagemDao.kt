package com.example.od2characterapp.data.local.dao


import androidx.room.*
import com.example.od2characterapp.data.local.entity.PersonagemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonagemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personagem: PersonagemEntity)

    @Update
    suspend fun update(personagem: PersonagemEntity)

    @Delete
    suspend fun delete(personagem: PersonagemEntity)

    @Query("SELECT * FROM personagens")
    fun getAll(): Flow<List<PersonagemEntity>>
}