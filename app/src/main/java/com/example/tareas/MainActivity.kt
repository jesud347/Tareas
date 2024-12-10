package com.example.tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Principal() {
    var tareas by remember {
        mutableStateOf(
            listOf(
                Tarea(1, "Crear Whattsap 2", prioridad.ALTA, false),
                Tarea(2, "Hacer la compra", prioridad.BAJA, true),
                Tarea(3, "Estudiar Acceso a Datos", prioridad.MEDIA, false),
                Tarea(4, "Comprar un videojuego", prioridad.BAJA, false),
                Tarea(5, "Arreglar el portatil", prioridad.ALTA, false)
            )
        )
    }

    Scaffold()
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Tareas Pendientes",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Listado(
                tareas = tareas.filter { !it.completada },
                onMarkCompleted = { tarea ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(completada = true) else it
                    }
                },
                onChangePriority = { tarea, nuevaPrioridad ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(prioridad = nuevaPrioridad) else it
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tareas Completadas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Listado(
                tareas = tareas.filter { it.completada },
                onMarkCompleted = { tarea ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(completada = false) else it
                    }
                },
                onChangePriority = { tarea, nuevaPrioridad ->
                    tareas = tareas.map {
                        if (it.id == tarea.id) it.copy(prioridad = nuevaPrioridad) else it
                    }
                }
            )
        }
    }
}

