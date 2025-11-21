package com.example.od2characterapp.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.od2characterapp.data.repository.PersonagemRepository

class PersonagemViewModelFactory(
    private val repo: PersonagemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PersonagemViewModel::class.java)) {
            return PersonagemViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
