package com.example.runapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onOpenMap: () -> Unit,
    onLogout: () -> Unit
) {
    var isRunning by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TOPO
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onLogout) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Black)
                }
                LinearProgressIndicator(
                    progress = if (isRunning) 0.6f else 0.3f,
                    color = Color(0xFF204E3A),
                    modifier = Modifier.width(100.dp).height(6.dp)
                )
                Text("32°C", color = Color(0xFF204E3A))
            }

            // CENTRO
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("00,00", fontSize = 64.sp, fontWeight = FontWeight.Bold)
                Text("Distance (Km)", color = Color.Gray)
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("Runner", "0'00''", "Avg Pace")
                    StatItem("Clock", "00.00", "Duration")
                    StatItem("Fire", "0 kcal", "Calories")
                }
            }

            // FUNDO
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Mini player
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF4A6155)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(Icons.Filled.Pause, contentDescription = null, tint = Color.White)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Sweet Disposition", color = Color.White, fontWeight = FontWeight.Bold)
                            Text("The Temper Trap", color = Color.White.copy(alpha = 0.7f))
                        }
                        Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botões principais
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // MAPA
                    OutlinedButton(
                        onClick = onOpenMap,
                        shape = CircleShape,
                        border = BorderStroke(2.dp, Color(0xFF204E3A)),
                        modifier = Modifier.size(70.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF204E3A))
                    ) {
                        Icon(Icons.Filled.Map, contentDescription = "Abrir Mapa")
                    }

                    // PAUSE / CONTINUE
                    Button(
                        onClick = { isRunning = !isRunning },
                        shape = CircleShape,
                        modifier = Modifier.size(80.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF204E3A))
                    ) {
                        Text(
                            text = if (isRunning) "PAUSE" else "CONTINUE",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // FINISH
                    Button(
                        onClick = { /* aqui podes guardar a corrida e voltar ao início no futuro */ },
                        shape = CircleShape,
                        modifier = Modifier.size(80.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("FINISH", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(icon: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(label, color = Color.Gray, fontSize = 12.sp)
    }
}