package com.example.runapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runapp.network.LeaderboardItem
import com.example.runapp.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel : ViewModel() {
    private val _leaderboard = MutableStateFlow<List<LeaderboardItem>>(emptyList())
    val leaderboard: StateFlow<List<LeaderboardItem>> = _leaderboard

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadLeaderboard() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.apiService.getLeaderboard()
                _leaderboard.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _leaderboard.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}