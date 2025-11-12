package com.example.od2characterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.od2characterapp.data.local.entity.AtributosEntity
import com.example.od2characterapp.domain.model.ClasseType
import com.example.od2characterapp.domain.model.RacaType
import com.example.od2characterapp.domain.util.AtributosGenerator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CreatePersonagemScreen(
    onSave: (String, String, String, AtributosEntity) -> Unit,
    onCancel: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var selectedRaca by remember { mutableStateOf(RacaType.HUMANO) }
    var selectedClasse by remember { mutableStateOf(ClasseType.GUERREIRO) }
    var atributos by remember { mutableStateOf(AtributosGenerator.classica()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Criar Novo Personagem", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Personagem") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))
        Text("Raça", fontWeight = FontWeight.Medium)
        FlowRow {
            RacaType.values().forEach { r ->
                FilterChip(
                    selected = selectedRaca == r,
                    onClick = { selectedRaca = r },
                    label = { Text(r.display) },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Classe", fontWeight = FontWeight.Medium)
        FlowRow  {
            ClasseType.values().forEach { c ->
                FilterChip(
                    selected = selectedClasse == c,
                    onClick = { selectedClasse = c },
                    label = { Text(c.display) },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Distribuição de atributos", fontWeight = FontWeight.Medium)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { atributos = AtributosGenerator.classica() }) { Text("Clássica") }
            Button(onClick = { atributos = AtributosGenerator.heroica() }) { Text("Heróica") }
            Button(onClick = { atributos = AtributosGenerator.aventureiro() }) { Text("Aventureiro") }
        }

        Spacer(Modifier.height(16.dp))
        Text("Atributos: FOR ${atributos.forca}  •  DES ${atributos.destreza}  •  CON ${atributos.constituicao}")
        Text("INT ${atributos.inteligencia}  •  SAB ${atributos.sabedoria}  •  CAR ${atributos.carisma}")

        Spacer(Modifier.height(28.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                val attrEntity = AtributosEntity(
                    atributos.forca, atributos.destreza, atributos.constituicao,
                    atributos.inteligencia, atributos.sabedoria, atributos.carisma
                )
                onSave(nome, selectedRaca.name, selectedClasse.name, attrEntity)
            }) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Salvar")
            }
            OutlinedButton(onClick = onCancel) {
                Icon(Icons.Default.Cancel, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Cancelar")
            }
        }
    }
}
