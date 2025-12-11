package com.example.runapp.tracking

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RunTrackingViewModel(app: Application) : AndroidViewModel(app) {

    private val fusedClient = LocationServices.getFusedLocationProviderClient(app)

    private val _stats = MutableStateFlow(RunStats())
    val stats: StateFlow<RunStats> = _stats

    private var lastLocation: Location? = null
    private var locationCallback: LocationCallback? = null

    private var timerJob: Job? = null
    private var startTimeMillis: Long = 0L

    fun startRun() {
        if (_stats.value.isRunning) return

        val now = System.currentTimeMillis()
        if (startTimeMillis == 0L) {
            startTimeMillis = now
        }

        _stats.value = _stats.value.copy(isRunning = true)

        startTimer()
        startLocationUpdates()
    }

    fun pauseRun() {
        if (!_stats.value.isRunning) return

        _stats.value = _stats.value.copy(isRunning = false)

        stopTimer()
        stopLocationUpdates()
    }

    fun stopRunAndReset() {
        pauseRun()
        lastLocation = null
        startTimeMillis = 0L
        _stats.value = RunStats()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_stats.value.isRunning) {
                val elapsed = System.currentTimeMillis() - startTimeMillis
                _stats.value = _stats.value.copy(
                    elapsedMillis = elapsed,
                    paceText = calculatePace(
                        distKm = _stats.value.distanceMeters / 1000.0,
                        elapsedMillis = elapsed
                    )
                )
                delay(1000L)
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun startLocationUpdates() {
        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            2000L
        )
            .setMinUpdateDistanceMeters(2f)
            .build()

        if (locationCallback == null) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    for (loc in result.locations) {
                        handleNewLocation(loc)
                    }
                }
            }
        }

        try {
            fusedClient.requestLocationUpdates(
                request,
                locationCallback!!,
                null
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun stopLocationUpdates() {
        locationCallback?.let { fusedClient.removeLocationUpdates(it) }
    }

    private fun handleNewLocation(location: Location) {
        val prev = lastLocation
        lastLocation = location

        if (prev != null) {
            val distance = prev.distanceTo(location)

            if (distance in 0.5f..50f) {
                val newTotal = _stats.value.distanceMeters + distance
                val elapsed = System.currentTimeMillis() - startTimeMillis

                _stats.value = _stats.value.copy(
                    distanceMeters = newTotal,
                    elapsedMillis = elapsed,
                    paceText = calculatePace(
                        distKm = newTotal / 1000.0,
                        elapsedMillis = elapsed
                    )
                )
            }
        }
    }

    private fun calculatePace(distKm: Double, elapsedMillis: Long): String {
        if (distKm <= 0.0) return "--:--"

        val totalMinutes = elapsedMillis / 1000.0 / 60.0
        val pace = totalMinutes / distKm

        val paceMin = pace.toInt()
        val paceSec = ((pace - paceMin) * 60).toInt()

        return "%d:%02d".format(paceMin, paceSec)
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationUpdates()
        stopTimer()
    }
}
