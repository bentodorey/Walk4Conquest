package com.example.runapp.screens

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.runapp.services.LocationTrackingService
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RunningScreen(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current

    var isRunning by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Estatísticas da corrida
    var distance by remember { mutableStateOf(0f) } // km
    var duration by remember { mutableStateOf(0) } // segundos
    var currentLatitude by remember { mutableStateOf(0.0) }
    var currentLongitude by remember { mutableStateOf(0.0) }

    // Permissões
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
    }

    // Cronómetro
    LaunchedEffect(isRunning, isPaused) {
        if (isRunning && !isPaused) {
            while (true) {
                delay(1000L)
                duration++
            }
        }
    }

    // Receiver para receber atualizações de localização
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == LocationTrackingService.ACTION_LOCATION_UPDATE) {
                    currentLatitude = intent.getDoubleExtra(LocationTrackingService.EXTRA_LATITUDE, 0.0)
                    currentLongitude = intent.getDoubleExtra(LocationTrackingService.EXTRA_LONGITUDE, 0.0)
                    distance = intent.getFloatExtra(LocationTrackingService.EXTRA_DISTANCE, 0f)
                }
            }
        }

        val filter = IntentFilter(LocationTrackingService.ACTION_LOCATION_UPDATE)
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Corrida em Andamento") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Stop, "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Estatísticas
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Cronómetro
                Text(
                    text = formatDuration(duration),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Distância
                StatCard(
                    label = "Distância",
                    value = String.format("%.2f km", distance)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Velocidade média (km/h)
                val avgSpeed = if (duration > 0) {
                    (distance / duration) * 3600
                } else 0f

                StatCard(
                    label = "Velocidade Média",
                    value = String.format("%.1f km/h", avgSpeed)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ritmo (min/km)
                val pace = if (distance > 0) {
                    duration / distance / 60
                } else 0f

                StatCard(
                    label = "Ritmo",
                    value = String.format("%.0f'%.0f\"/km",
                        pace.toInt().toFloat(),
                        (pace % 1 * 60))
                )
            }

            // Botões de controlo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (!isRunning) {
                    // Botão Iniciar
                    FloatingActionButton(
                        onClick = {
                            if (hasLocationPermission) {
                                startRunning(context)
                                isRunning = true
                                isPaused = false
                            } else {
                                permissionLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    )
                                )
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Default.PlayArrow, "Iniciar", modifier = Modifier.size(32.dp))
                    }
                } else {
                    // Botão Pausar/Retomar
                    FloatingActionButton(
                        onClick = {
                            isPaused = !isPaused
                        },
                        containerColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Icon(
                            if (isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                            if (isPaused) "Retomar" else "Pausar",
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Botão Parar
                    FloatingActionButton(
                        onClick = {
                            stopRunning(context)
                            isRunning = false
                            isPaused = false
                            // TODO: Finalizar percurso no backend
                        },
                        containerColor = MaterialTheme.colorScheme.error
                    ) {
                        Icon(Icons.Default.Stop, "Parar", modifier = Modifier.size(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun formatDuration(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, secs)
}

private fun startRunning(context: Context) {
    val intent = Intent(context, LocationTrackingService::class.java).apply {
        action = LocationTrackingService.ACTION_START_TRACKING
        putExtra(LocationTrackingService.EXTRA_PERCURSO_ID, 1L) // TODO: ID real do percurso
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
    }
}

private fun stopRunning(context: Context) {
    val intent = Intent(context, LocationTrackingService::class.java).apply {
        action = LocationTrackingService.ACTION_STOP_TRACKING
    }
    context.stopService(intent)
}