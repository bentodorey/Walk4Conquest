package com.example.runapp.tracking

data class RunStats(
    val distanceMeters: Float = 0f,
    val elapsedMillis: Long = 0L,
    val paceText: String = "--:--",
    val isRunning: Boolean = false
)
