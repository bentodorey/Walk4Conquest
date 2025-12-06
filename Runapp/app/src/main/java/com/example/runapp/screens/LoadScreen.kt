package com.example.runapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runapp.R

@Composable
fun LoadScreen(
    onJoinClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1E2D26)
    ) {
        // Background com Tint
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Background of people running",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = Color(0xFF204E3A),
                blendMode = BlendMode.Color
            )
        )

        // --- CONTEÚDO CENTRADO ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ⭐ LOGOTIPO CENTRADO
            Image(
                painter = painterResource(id = R.drawable.logonoback),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(bottom = 24.dp)
            )

            // TEXTO CENTRADO
            Text(
                text = "Conquest your friends step by step",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // BOTÃO JOIN
            Button(
                onClick = onJoinClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF204E3A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Join Us", color = Color.White, fontSize = 16.sp)
            }

            Spacer(Modifier.height(16.dp))

            // BOTÃO LOGIN
            OutlinedButton(
                onClick = onLoginClick,
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Log In", fontSize = 16.sp)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoadScreenPreview() {
    LoadScreen()
}
