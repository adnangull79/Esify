package com.example.esify.data.auth

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

sealed class AuthResult<out T> {
    data class Success<out T>(val data: T) : AuthResult<T>()
    data class Error(val exception: Throwable, val userMessage: String) : AuthResult<Nothing>()
}

class AuthRepository(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    val currentUser: FirebaseUser? get() = firebaseAuth.currentUser
    val isUserLoggedIn: Boolean get() = currentUser != null

    suspend fun signUpWithEmail(
        fullName: String,
        email: String,
        password: String
    ): AuthResult<FirebaseUser> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email.trim(), password).await()
            val user = authResult.user
            if (user != null && fullName.isNotBlank()) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullName.trim())
                    .build()
                user.updateProfile(profileUpdates).await()
            }
            if (user != null) {
                try {
                    user.sendEmailVerification().await()
                } catch (_: Exception) {
                    // Non-fatal if verification email send fails
                }
                firebaseAuth.signOut()
                AuthResult.Success(user)
            } else {
                AuthResult.Error(
                    IllegalStateException("Failed to create user."),
                    "Account creation failed. Please try again."
                )
            }
        } catch (e: Exception) {
            AuthResult.Error(e, mapFirebaseErrorMessage(e))
        }
    }

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): AuthResult<FirebaseUser> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email.trim(), password).await()
            val user = authResult.user
            if (user != null) {
                AuthResult.Success(user)
            } else {
                AuthResult.Error(
                    IllegalStateException("User sign in returned null."),
                    "Sign in failed. Please try again."
                )
            }
        } catch (e: Exception) {
            AuthResult.Error(e, mapFirebaseErrorMessage(e))
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    private fun mapFirebaseErrorMessage(e: Exception): String {
        val message = e.message ?: ""
        return when {
            e is FirebaseNetworkException || message.contains("network", ignoreCase = true) || message.contains("connection", ignoreCase = true) ->
                "Network error. Please check your internet connection and try again."
            message.contains("badly formatted", ignoreCase = true) || message.contains("invalid email", ignoreCase = true) ->
                "Please enter a valid email address."
            message.contains("already in use", ignoreCase = true) || message.contains("collision", ignoreCase = true) ->
                "An account with this email address already exists. Please login instead."
            message.contains("at least 6 characters", ignoreCase = true) || message.contains("weak password", ignoreCase = true) ->
                "Password must be at least 6 characters long."
            message.contains("no user record", ignoreCase = true) || message.contains("user-not-found", ignoreCase = true) ->
                "No account found with this email address."
            message.contains("INVALID_LOGIN_CREDENTIALS", ignoreCase = true) ||
            message.contains("wrong-password", ignoreCase = true) ||
            message.contains("incorrect", ignoreCase = true) ->
                "Incorrect email or password. Please try again."
            else -> "Authentication failed. Please check your input and internet connection."
        }
    }
}
