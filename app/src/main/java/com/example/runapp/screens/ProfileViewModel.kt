package com.example.runapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runapp.network.RetrofitClient
import com.example.runapp.network.UserProfileResponse
import com.example.runapp.utils.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val profile: UserProfileResponse? = null
)

class ProfileViewModel : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileState

    private val _profile = MutableStateFlow<UserProfileResponse?>(null)
    val profile: StateFlow<UserProfileResponse?> = _profile

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

                val response = RetrofitClient.apiService.getUserProfile("Bearer $token")

                _profile.value = response
                _profileState.value = ProfileUiState(
                    isLoading = false,
                    profile = response
                )

            } catch (e: Exception) {
                _profileState.value = ProfileUiState(
                    isLoading = false,
                    error = e.message ?: "Erro ao carregar perfil"
                )
            }
        }
    }
}