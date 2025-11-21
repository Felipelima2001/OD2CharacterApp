package com.example.od2characterapp.data.repository

import com.example.od2characterapp.data.local.dao.PersonagemDao
import com.example.od2characterapp.data.local.entity.PersonagemEntity
import kotlinx.coroutines.flow.Flow

class PersonagemRepository(private val dao: PersonagemDao) {

    fun getAll(): Flow<List<PersonagemEntity>> = dao.getAll()

    suspend fun save(personagem: PersonagemEntity) {
        dao.insert(personagem)
    }

    suspend fun delete(personagem: PersonagemEntity) {
        dao.delete(personagem)
    }

    suspend fun findById(id: Int) = dao.findById(id)

    suspend fun updateHp(id: Int, hp: Int) = dao.updateHp(id, hp)
}
