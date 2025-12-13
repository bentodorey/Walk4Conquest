package com.example.runapp.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.runapp.MainActivity
import com.example.runapp.R
import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationTrackingService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "location_tracking_channel"
        const val NOTIFICATION_ID = 1
        const val ACTION_START_TRACKING = "ACTION_START_TRACKING"
        const val ACTION_STOP_TRACKING = "ACTION_STOP_TRACKING"
        const val EXTRA_PERCURSO_ID = "EXTRA_PERCURSO_ID"

        // Broadcast actions
        const val ACTION_LOCATION_UPDATE = "com.example.runapp.LOCATION_UPDATE"
        const val EXTRA_LATITUDE = "EXTRA_LATITUDE"
        const val EXTRA_LONGITUDE = "EXTRA_LONGITUDE"
        const val EXTRA_DISTANCE = "EXTRA_DISTANCE"
    }

    private var percursoId: Long? = null
    private var lastLocation: Location? = null
    private var totalDistance: Float = 0f

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_TRACKING -> {
                percursoId = intent.getLongExtra(EXTRA_PERCURSO_ID, -1)
                startForegroundService()
                startLocationUpdates()
            }
            ACTION_STOP_TRACKING -> {
                stopLocationUpdates()
                stopSelf()
            }
        }
        return START_STICKY
    }

    private fun startForegroundService() {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Rastreamento de Corrida",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notificação para rastreamento GPS durante a corrida"
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Corrida em andamento")
            .setContentText("Rastreando sua localização...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            5000L // Atualizar a cada 5 segundos
        ).apply {
            setMinUpdateIntervalMillis(2000L) // Mínimo de 2 segundos
            setWaitForAccurateLocation(true)
        }.build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    handleNewLocation(location)
                }
            }
        }

        // Verificar permissões
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun handleNewLocation(location: Location) {
        // Calcular distância desde o último ponto
        lastLocation?.let { last ->
            val distance = last.distanceTo(location)
            if (distance > 5) { // Apenas se moveu mais de 5 metros
                totalDistance += distance

                // Broadcast da nova localização
                val intent = Intent(ACTION_LOCATION_UPDATE).apply {
                    putExtra(EXTRA_LATITUDE, location.latitude)
                    putExtra(EXTRA_LONGITUDE, location.longitude)
                    putExtra(EXTRA_DISTANCE, totalDistance / 1000f) // Em km
                }
                sendBroadcast(intent)

                // TODO: Enviar para o backend
                sendLocationToBackend(location)
            }
        }

        lastLocation = location
    }

    private fun sendLocationToBackend(location: Location) {
        percursoId?.let { id ->
            serviceScope.launch {
                try {
                    // TODO: Implementar chamada à API
                    // val response = apiService.adicionarPonto(
                    //     id,
                    //     CoordenadaRequest(location.latitude, location.longitude)
                    // )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
        serviceJob.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}