package com.example.runapp.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runapp.network.RetrofitClient
import com.example.runapp.utils.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val profile: UserProfile? = null
)

class ProfileViewModel : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileState

    fun loadUserProfile(context: Context) {
        viewModelScope.launch {
            _profileState.value = ProfileUiState(isLoading = true)

            try {
                val token = TokenManager.getToken(context)

                if (token == null) {
                    _profileState.value = ProfileUiState(
                        isLoading = false,
                        error = "Token não encontrado. Faça login novamente."
                    )
                    return@launch
                }

                val response = RetrofitClient.api.getUserProfile("Bearer $token")

                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    val profile = UserProfile(
                        name = data.nome,
                        username = data.username,
                        email = data.email,
                        totalDistanceKm = 0.0, // TODO: buscar do backend
                        totalRuns = 0,          // TODO: buscar do backend
                        avgPace = "0'00\" /km"  // TODO: buscar do backend
                    )

                    _profileState.value = ProfileUiState(
                        isLoading = false,
                        profile = profile
                    )
                } else {
                    _profileState.value = ProfileUiState(
                        isLoading = false,
                        error = "Erro ao carregar perfil (${response.code()})"
                    )
                }

            } catch (e: Exception) {
                _profileState.value = ProfileUiState(
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar perfil"
                )
            }
        }
    }
}