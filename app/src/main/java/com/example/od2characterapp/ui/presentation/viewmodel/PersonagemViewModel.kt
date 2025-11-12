package com.example.od2characterapp.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.od2characterapp.data.local.entity.AtributosEntity
import com.example.od2characterapp.data.local.entity.PersonagemEntity
import com.example.od2characterapp.data.repository.PersonagemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PersonagemViewModel(private val repo: PersonagemRepository) : ViewModel() {

    val personagens = repo.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun savePersonagem(nome: String, raca: String, classe: String, attr: AtributosEntity) {
        val entity = PersonagemEntity(
            nome = nome,
            raca = raca,
            classe = classe,
            atributos = attr
        )
        viewModelScope.launch {
            repo.save(entity)
        }
    }

    fun deletePersonagem(entity: PersonagemEntity) {
        viewModelScope.launch {
            repo.delete(entity)
        }
    }
}
