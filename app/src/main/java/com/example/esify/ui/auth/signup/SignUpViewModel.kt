package com.example.esify.ui.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esify.data.auth.AuthRepository
import com.example.esify.data.auth.AuthResult
import kotlinx.coroutines.launch

data class SignUpUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class SignUpViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        onSuccess: (email: String) -> Unit
    ) {
        if (uiState.isLoading) return

        uiState = SignUpUiState(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            val result = authRepository.signUpWithEmail(fullName, email, password)
            when (result) {
                is AuthResult.Success -> {
                    uiState = SignUpUiState(isLoading = false, isSuccess = true)
                    onSuccess(email.trim())
                }
                is AuthResult.Error -> {
                    uiState = SignUpUiState(isLoading = false, errorMessage = result.userMessage)
                }
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }
}
