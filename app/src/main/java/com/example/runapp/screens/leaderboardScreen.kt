package com.example.runapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.runapp.viewmodels.LeaderboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    onBack: () -> Unit = {},
    viewModel: LeaderboardViewModel = viewModel()
) {
    val leaderboard by viewModel.leaderboard.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLeaderboard()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Leaderboard") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                leaderboard.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            modifier = Modifier.size(80.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Nenhum utilizador ainda",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(leaderboard) { index, user ->
                            LeaderboardRow(
                                position = index + 1,
                                nome = user.nome,
                                username = user.username,
                                pontos = user.pontos,
                                distanciaKm = user.total_distancia_km ?: 0.0,
                                corridas = user.total_corridas ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LeaderboardRow(
    position: Int,
    nome: String,
    username: String,
    pontos: Int,
    distanciaKm: Double,
    corridas: Int
) {
    val medalColor = when (position) {
        1 -> Color(0xFFFFD700) // Ouro
        2 -> Color(0xFFC0C0C0) // Prata
        3 -> Color(0xFFCD7F32) // Bronze
        else -> Color.Transparent
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (position <= 3) medalColor.copy(alpha = 0.1f) else Color.White,
        shadowElevation = if (position <= 3) 6.dp else 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Posição com medalha
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (position <= 3) medalColor else Color(0xFF204E3A)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$position",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (position <= 3) Color.Black else Color.White
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Círculo com inicial
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF204E3A)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = nome.firstOrNull()?.uppercaseChar()?.toString() ?: "?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = "@$username",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(
                        text = "${String.format("%.1f", distanciaKm)} km",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF204E3A)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$corridas corridas",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            // Pontos
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$pontos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF204E3A)
                )
                Text(
                    text = "pontos",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}