package com.example.esify.ui.auth

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.R

private object LoginColors {
    val DarkGreen = Color(0xFF0B4A33)
    val BgTop = Color(0xFFF4FAF6)
    val BgBottom = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF14211B)
    val TextSecondary = Color(0xFF5F6D66)
    val Hint = Color(0xFF8A9690)
    val ChipBg = Color(0xFFE3F2E9)
    val FieldBorder = Color(0xFFE3EBE6)
    val SignUpBg = Color(0xFFE6F3EB)
    val Wave = Color(0xFFE9F5EE)
}

@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onLogin: (email: String, password: String, rememberMe: Boolean) -> Unit = { _, _, _ -> },
    onForgotPassword: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onContinueToHomeClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }

    val emailLooksValid = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val formValid = emailLooksValid && password.isNotEmpty()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(LoginColors.BgTop, LoginColors.BgBottom))),
    ) {
        // Decorative wave at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(topStart = 240.dp, topEnd = 240.dp))
                .background(LoginColors.Wave),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = LoginColors.DarkGreen,
                modifier = Modifier
                    .size(26.dp)
                    .clickable { onBack() },
            )

            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Welcome\nBack",
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = LoginColors.DarkGreen,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Login to continue\nyour learning journey\nwith Esify.",
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        color = LoginColors.TextSecondary,
                    )
                }
                LoginIllustration()
            }

            Spacer(Modifier.height(14.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .border(1.dp, LoginColors.FieldBorder, RoundedCornerShape(20.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                LoginField(
                    icon = Icons.Filled.Email,
                    label = "Email Address",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter your email address",
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                )
                LoginField(
                    icon = Icons.Filled.Lock,
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter your password",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onToggleVisibility = { passwordVisible = !passwordVisible },
                )
            }

            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { rememberMe = !rememberMe },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(if (rememberMe) LoginColors.DarkGreen else Color.White)
                            .border(
                                1.5.dp,
                                if (rememberMe) LoginColors.DarkGreen else LoginColors.Hint,
                                RoundedCornerShape(5.dp),
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (rememberMe) {
                            Icon(
                                Icons.Filled.Check, null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp),
                            )
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Remember me",
                        fontSize = 11.sp,
                        color = LoginColors.TextPrimary,
                    )
                }
                Text(
                    text = "Forgot Password?",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoginColors.DarkGreen,
                    modifier = Modifier.clickable { onForgotPassword() },
                )
            }

            Spacer(Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(LoginColors.DarkGreen)
                    .alpha(if (formValid) 1f else 0.6f)
                    .clickable(enabled = formValid) {
                        onLogin(email.trim(), password, rememberMe)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward, null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = LoginColors.FieldBorder)
                Text(
                    text = "OR",
                    fontSize = 9.sp,
                    color = LoginColors.TextSecondary,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = LoginColors.FieldBorder)
            }

            Spacer(Modifier.height(14.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(LoginColors.SignUpBg)
                    .clickable { onSignUpClick() }
                    .padding(vertical = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 11.sp,
                    color = LoginColors.TextPrimary,
                )
                Text(
                    text = "Sign Up",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoginColors.DarkGreen,
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward, null,
                    tint = LoginColors.DarkGreen,
                    modifier = Modifier.size(15.dp),
                )
            }

            // Temporary developer button to move to Home screen
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onContinueToHomeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, LoginColors.FieldBorder)
            ) {
                Text(
                    text = "Go to Home",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = LoginColors.DarkGreen
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

// ---------------------------------------------------------------------------
// Text field styled like the design
// ---------------------------------------------------------------------------

@Composable
private fun LoginField(
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onToggleVisibility: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, LoginColors.FieldBorder, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LoginColors.ChipBg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, null, tint = LoginColors.DarkGreen, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = LoginColors.TextPrimary,
            )
            Spacer(Modifier.height(4.dp))
            Box {
                if (value.isEmpty()) {
                    Text(text = placeholder, fontSize = 12.sp, color = LoginColors.Hint)
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 12.sp, color = LoginColors.TextPrimary),
                    cursorBrush = SolidColor(LoginColors.DarkGreen),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                    ),
                    visualTransformation = if (isPassword && !passwordVisible)
                        PasswordVisualTransformation() else VisualTransformation.None,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        if (isPassword) {
            Icon(
                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                tint = LoginColors.TextPrimary,
                modifier = Modifier
                    .size(22.dp)
                    .clickable { onToggleVisibility() },
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Illustration
// ---------------------------------------------------------------------------

@Composable
private fun LoginIllustration() {
    Image(
        painter = painterResource(id = R.drawable.ic_signup_illustration),
        contentDescription = "Graduation Cap and Books Illustration",
        modifier = Modifier.size(width = 140.dp, height = 125.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme { LoginScreen() }
}
