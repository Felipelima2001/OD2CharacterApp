package com.example.od2characterapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.od2characterapp.data.local.entity.PersonagemEntity
import com.example.od2characterapp.service.BattleService
import com.example.od2characterapp.ui.presentation.viewmodel.PersonagemViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPersonagensScreen(viewModel: PersonagemViewModel, onAddClick: () -> Unit) {
    val personagens by viewModel.personagens.collectAsState()
    val context = LocalContext.current   // ✔ PEGUE O CONTEXTO AQUI

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
                        PersonagemCard(
                            personagem = personagem,
                            onDelete = { viewModel.deletePersonagem(it) },

                            // ✔ AGORA USANDO O CONTEXTO DO COMPOSABLE
                            onStartBattle = { personagemId ->
                                val intent = Intent(context, BattleService::class.java)
                                intent.putExtra("id", personagemId)
                                ContextCompat.startForegroundService(context, intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PersonagemCard(
    personagem: PersonagemEntity,
    onDelete: (PersonagemEntity) -> Unit,
    onStartBattle: (Int) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {

            Row(Modifier.fillMaxWidth()) {
                Text(
                    personagem.nome,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Botão de deletar
                IconButton(onClick = { onDelete(personagem) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                }
            }

            Text("Raça: ${personagem.raca} | Classe: ${personagem.classe}")

            Spacer(Modifier.height(4.dp))

            Text(
                "FOR ${personagem.atributos.forca} | DES ${personagem.atributos.destreza} | " +
                        "CON ${personagem.atributos.constituicao} | INT ${personagem.atributos.inteligencia} | " +
                        "SAB ${personagem.atributos.sabedoria} | CAR ${personagem.atributos.carisma}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "HP Atual: ${personagem.currentHp}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            // Botão de iniciar batalha
            Button(
                onClick = { onStartBattle(personagem.id) }
            ) {
                Text("Iniciar Batalha")
            }
        }
    }
}
