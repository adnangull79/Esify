package com.example.esify.ui.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esify.data.auth.AuthRepository
import com.example.esify.data.auth.AuthResult
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun login(
        email: String,
        password: String,
        rememberMe: Boolean,
        onSuccess: () -> Unit
    ) {
        if (uiState.isLoading) return

        uiState = LoginUiState(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = authRepository.signInWithEmail(email, password)
            when (result) {
                is AuthResult.Success -> {
                    uiState = LoginUiState(isLoading = false, isSuccess = true)
                    onSuccess()
                }
                is AuthResult.Error -> {
                    uiState = LoginUiState(isLoading = false, errorMessage = result.userMessage)
                }
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }
}
