package com.example.runapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(onLogout: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F9F8) // fundo claro, visível
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título principal
            Text(
                text = "🏃‍♂️ Ready to Run?",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF204E3A)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botão principal
            Button(
                onClick = { /* TODO: iniciar corrida futuramente */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF204E3A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Start Running", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de logout
            OutlinedButton(
                onClick = onLogout,
                border = BorderStroke(1.dp, Color(0xFF204E3A)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF204E3A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Logout", fontSize = 16.sp)
            }
        }
    }
}

