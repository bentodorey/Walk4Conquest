package com.example.runapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Modelo de cada entrada na leaderboard
data class LeaderboardEntry(
    val name: String,
    val distanceKm: Double
)

@Composable
fun LeaderboardScreen(onBack: () -> Unit) {

    // Dados temporários (mais tarde ligamos ao backend)
    val runners = remember {
        listOf(
            LeaderboardEntry("Tiago", 42.3),
            LeaderboardEntry("Maria", 30.1),
            LeaderboardEntry("João", 18.7),
            LeaderboardEntry("Ana", 12.5),
            LeaderboardEntry("Bento", 8.2)
        ).sortedByDescending { it.distanceKm }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            // Barra de topo
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Leaderboard",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Lista dos utilizadores
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(runners) { index, entry ->
                    LeaderboardRow(
                        position = index + 1,
                        entry = entry
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun LeaderboardRow(
    position: Int,
    entry: LeaderboardEntry
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Posição (ex: 1º)
            Text(
                text = position.toString() + "º", // ← corrigido
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Círculo com inicial
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF204E3A)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = entry.name.first().uppercaseChar().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Nome + label
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Total distance",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = String.format("%.1f km", entry.distanceKm),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LeaderboardPreview() {
    MaterialTheme {
        LeaderboardScreen(onBack = {})
    }
}
