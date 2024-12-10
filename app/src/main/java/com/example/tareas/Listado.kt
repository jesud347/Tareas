package com.example.tareas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Listado(
    tareas: List<Tarea>,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        tareas.forEach { tarea ->
            Componente(
                tarea = tarea,
                onMarkCompleted = onMarkCompleted,
                onChangePriority = onChangePriority
            )
        }
    }
}

@Composable
fun Componente(
    tarea: Tarea,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = if (tarea.completada) {
                            listOf(Color(0xFF00C4CC), Color(0xFF006B6B))
                        } else {
                            listOf(Color(0xFF99FF66), Color(0xFF66CC33))
                        }
                    )
                )
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = when (tarea.prioridad) {
                                prioridad.ALTA -> Color.Red
                                prioridad.MEDIA -> Color.Yellow
                                prioridad.BAJA -> Color.Green
                            },
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = tarea.descripcion,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )

                menuDesplegable(
                    tarea = tarea,
                    onMarkCompleted = onMarkCompleted,
                    onChangePriority = onChangePriority
                )
            }
        }
    }
}

@Composable
fun menuDesplegable(
    tarea: Tarea,
    onMarkCompleted: (Tarea) -> Unit,
    onChangePriority: (Tarea, prioridad) -> Unit
) {
    var desplegado by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { desplegado = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White
            ),
        ) {
            Icon(Icons.Default.Settings, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Opciones")
        }

        DropdownMenu(
            expanded = desplegado,
            onDismissRequest = { desplegado = false }
        ) {
            DropdownMenuItem(
                text = { Text(if (tarea.completada) "Cambiar a Pendiente" else "Cambiar a Completada") },
                onClick = {
                    desplegado = false
                    onMarkCompleted(tarea)
                },
            )
            DropdownMenuItem(
                text = { Text("Prioridad -> ALTA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.ALTA)
                },
            )
            DropdownMenuItem(
                text = { Text("Prioridad -> MEDIA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.MEDIA)
                },
            )
            DropdownMenuItem(
                text = { Text("Prioridad -> BAJA") },
                onClick = {
                    desplegado = false
                    onChangePriority(tarea, prioridad.BAJA)
                },
            )
        }
    }
}
