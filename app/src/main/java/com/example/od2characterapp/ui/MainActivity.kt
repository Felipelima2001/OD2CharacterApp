package com.example.od2characterapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import com.example.od2characterapp.App
import com.example.od2characterapp.data.repository.PersonagemRepository
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModel
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModelFactory
import com.example.od2characterapp.ui.screens.CreatePersonagemScreen
import com.example.od2characterapp.ui.screens.ListPersonagensScreen

class MainActivity : ComponentActivity() {

    // permissão de notificação
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SOLICITA PERMISSÃO DE NOTIFICAÇÃO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            val granted = ContextCompat.checkSelfPermission(this, permission)

            if (granted != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(permission)
            }
        }

        // ----- CONFIG DO VIEWMODEL -----
        val dao = App.database.personagemDao()
        val repo = PersonagemRepository(dao)
        val viewModel = PersonagemViewModelFactory(repo).create(PersonagemViewModel::class.java)

        // ----- UI (COMPOSE) -----
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
