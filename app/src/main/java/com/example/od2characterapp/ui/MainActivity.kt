package com.example.od2characterapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import com.example.od2characterapp.App
import com.example.od2characterapp.data.repository.PersonagemRepository
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModel
import com.example.od2characterapp.ui.screens.ListPersonagensScreen
import com.example.od2characterapp.ui.screens.CreatePersonagemScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: PersonagemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = App.database.personagemDao()
        val repo = PersonagemRepository(dao)
        viewModel = PersonagemViewModelFactory(repo).create(PersonagemViewModel::class.java)

        setContent {
            com.example.od2characterapp.ui.theme.OD2CharacterTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var showCreate by remember { mutableStateOf(false) }

                    if (showCreate) {
                        CreatePersonagemScreen(
                            onSave = { nome, raca, classe, atributosEntity ->
                                viewModel.savePersonagem(nome, raca, classe, atributosEntity)
                                showCreate = false
                            },
                            onCancel = { showCreate = false }
                        )
                    } else {
                        ListPersonagensScreen(
                            viewModel = viewModel,
                            onAddClick = { showCreate = true }
                        )
                    }
                }
            }
        }

    }
        }


