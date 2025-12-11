package com.example.runapp.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.runapp.tracking.RunStats
import com.example.runapp.tracking.RunTrackingViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

// ---- Função helper para o tempo ----
fun formatTime(millis: Long): String {
    val totalSec = millis / 1000
    val h = totalSec / 3600
    val m = (totalSec % 3600) / 60
    val s = totalSec % 60

    return if (h > 0)
        "%d:%02d:%02d".format(h, m, s)
    else
        "%02d:%02d".format(m, s)
}

@Composable
fun MapScreen(onBack: () -> Unit) {

    // VIEWMODEL DO TRACKING
    val runViewModel: RunTrackingViewModel = viewModel()
    val stats by runViewModel.stats.collectAsState()

    val context = LocalContext.current

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Launcher para pedir as duas permissões
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val fine = result[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarse = result[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        hasLocationPermission = fine || coarse

        if (hasLocationPermission && !stats.isRunning) {
            runViewModel.startRun()
        }
    }

    // Pede logo permissão quando entra no ecrã (se ainda não tiver)
    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // Posição inicial: Lisboa (default)
    val lisboa = LatLng(38.7223, -9.1393)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisboa, 15f)
    }

    val uiSettings = remember(hasLocationPermission) {
        MapUiSettings(
            myLocationButtonEnabled = hasLocationPermission
        )
    }

    val properties = remember(hasLocationPermission) {
        MapProperties(
            isMyLocationEnabled = hasLocationPermission
        )
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // Mapa em fullscreen com localização real (ponto azul)
            GoogleMap(
                modifier = Modifier.matchParentSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            )

            // Top bar
            TopOverlay()

            // Parte de baixo (stats + botões)
            BottomOverlay(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                stats = stats,
                onPlayClick = {
                    if (!hasLocationPermission) {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    } else {
                        runViewModel.startRun()
                    }
                },
                onPauseClick = { runViewModel.pauseRun() }
            )
        }
    }
}

@Composable
private fun TopOverlay() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO abrir menu */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Cápsula da temperatura
            Surface(
                shape = RoundedCornerShape(50),
                color = Color.White,
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("☀️  32ºC", fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(0.dp))
    }
}

@Composable
private fun BottomOverlay(
    modifier: Modifier = Modifier,
    stats: RunStats,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {

        // CARD das métricas (fundo branco)
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(
                    title = "Avg Pace",
                    value = if (stats.paceText == "--:--") "0'00\"" else stats.paceText
                )

                StatItem(
                    title = "Duration",
                    value = formatTime(stats.elapsedMillis)
                )

                val kcal = (stats.distanceMeters / 1000f * 60).toInt()
                StatItem(
                    title = "Calories",
                    value = "$kcal kcal"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Botão PLAY
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color(0xFF204E3A),
                tonalElevation = 6.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onPlayClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start",
                        tint = Color.White
                    )
                }
            }

            // Botão PAUSE branco com borda
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color.White,
                tonalElevation = 4.dp,
                border = BorderStroke(2.dp, Color(0xFF222222))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onPauseClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Pause,
                            contentDescription = "Pause",
                            tint = Color(0xFF222222)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "PAUSE",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color(0xFF222222)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF111111)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color(0xFF777777)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MapScreenPreview() {
    MaterialTheme {
        MapScreen(onBack = {})
    }
}
