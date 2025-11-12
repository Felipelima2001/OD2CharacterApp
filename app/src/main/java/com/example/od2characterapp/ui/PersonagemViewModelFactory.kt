package com.example.od2characterapp.ui



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.od2characterapp.data.repository.PersonagemRepository
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModel

class PersonagemViewModelFactory(
    private val repo: PersonagemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonagemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonagemViewModel(repo) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
    }
}
