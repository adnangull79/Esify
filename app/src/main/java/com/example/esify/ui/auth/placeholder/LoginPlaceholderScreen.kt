package com.example.esify.ui.auth.placeholder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.esify.ui.auth.LoginScreen

@Composable
fun LoginPlaceholderScreen(
    onBackClick: () -> Unit,
    onContinueToHomeClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LoginScreen(
        onBack = onBackClick,
        onLogin = { _, _, _ -> onContinueToHomeClick() },
        onSignUpClick = onSignUpClick,
        onContinueToHomeClick = onContinueToHomeClick,
        modifier = modifier
    )
}
