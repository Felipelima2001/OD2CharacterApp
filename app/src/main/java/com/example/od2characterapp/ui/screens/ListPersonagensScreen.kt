package com.example.od2characterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.od2characterapp.data.local.entity.PersonagemEntity
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPersonagensScreen(viewModel: PersonagemViewModel, onAddClick: () -> Unit) {
    val personagens by viewModel.personagens.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Personagens Criados", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))

            if (personagens.isEmpty()) {
                Text("Nenhum personagem criado ainda.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(personagens) { personagem ->
                        PersonagemCard(personagem)
                    }
                }
            }
        }
    }
}

@Composable
fun PersonagemCard(personagem: PersonagemEntity) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(personagem.nome, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Raça: ${personagem.raca} | Classe: ${personagem.classe}")
            Spacer(Modifier.height(4.dp))
            Text(
                "FOR ${personagem.atributos.forca} | DES ${personagem.atributos.destreza} | " +
                        "CON ${personagem.atributos.constituicao} | INT ${personagem.atributos.inteligencia} | " +
                        "SAB ${personagem.atributos.sabedoria} | CAR ${personagem.atributos.carisma}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
