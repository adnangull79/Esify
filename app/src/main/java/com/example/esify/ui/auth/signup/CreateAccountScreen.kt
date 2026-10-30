package com.example.esify.ui.auth.signup

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esify.R
import com.example.esify.ui.theme.EsifyTheme

private const val USE_IMAGE_ILLUSTRATION = true

private object SignUpColors {
    val DarkGreen = Color(0xFF0B4A33)
    val MidGreen = Color(0xFF14654A)
    val Green = Color(0xFF1E8E5A)
    val BgTop = Color(0xFFF4FAF6)
    val BgBottom = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF14211B)
    val TextSecondary = Color(0xFF5F6D66)
    val Hint = Color(0xFF8A9690)
    val ChipBg = Color(0xFFE3F2E9)
    val FieldBorder = Color(0xFFE3EBE6)
    val LoginBg = Color(0xFFE6F3EB)
    val Wave = Color(0xFFE9F5EE)
    val Error = Color(0xFFE53935)
}

@Composable
fun CreateAccountScreen(
    onBack: () -> Unit = {},
    onCreateAccount: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState
    val isLoading = uiState.isLoading

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { errorMsg ->
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
    }

    val backgroundBrush = remember {
        Brush.verticalGradient(listOf(SignUpColors.BgTop, SignUpColors.BgBottom))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundBrush),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(topStart = 220.dp, topEnd = 220.dp))
                .background(SignUpColors.Wave),
        )

        SignUpForm(
            onBack = onBack,
            isLoading = isLoading,
            onCreateAccount = { name, email, password ->
                viewModel.signUp(name, email, password) { verifiedEmail ->
                    onCreateAccount(name, verifiedEmail, password)
                }
            },
            onLoginClick = onLoginClick,
        )

        // Full-Screen Blur & Loading Overlay
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
                    .clickable(enabled = false) {},
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 32.dp, vertical = 26.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(42.dp),
                        color = SignUpColors.DarkGreen,
                        strokeWidth = 3.5.dp
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Creating Account...",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = SignUpColors.DarkGreen
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Setting up your profile",
                        style = TextStyle(
                            fontSize = 11.5.sp,
                            color = SignUpColors.TextSecondary
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpForm(
    onBack: () -> Unit,
    isLoading: Boolean,
    onCreateAccount: (name: String, email: String, password: String) -> Unit,
    onLoginClick: () -> Unit,
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmVisible by rememberSaveable { mutableStateOf(false) }

    val passwordTooShort = password.isNotEmpty() && password.length < 6
    val mismatch = confirmPassword.isNotEmpty() && confirmPassword != password
    val emailLooksValid = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val formValid = fullName.isNotBlank() &&
            emailLooksValid &&
            password.length >= 6 &&
            confirmPassword == password

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(if (isLoading) Modifier.blur(16.dp) else Modifier)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = SignUpColors.DarkGreen,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(enabled = !isLoading) { onBack() },
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Create\nAccount",
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = SignUpColors.DarkGreen,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Join Esify and start your exam preparation journey today.",
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = SignUpColors.TextSecondary,
                    )
                }
                SignUpIllustration()
            }

            Spacer(Modifier.height(8.dp))

            SignUpField(
                icon = Icons.Filled.Person,
                label = "Full Name",
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "Enter your full name",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                enabled = !isLoading
            )

            Spacer(Modifier.height(8.dp))

            SignUpField(
                icon = Icons.Filled.Email,
                label = "Email Address",
                value = email,
                onValueChange = { email = it },
                placeholder = "Enter your email address",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                enabled = !isLoading
            )

            Spacer(Modifier.height(8.dp))

            SignUpField(
                icon = Icons.Filled.Lock,
                label = "Password",
                value = password,
                onValueChange = { password = it },
                placeholder = "Enter your password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                isPassword = true,
                passwordVisible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible },
                enabled = !isLoading
            )
            Text(
                text = "Password must be at least 6 characters long.",
                fontSize = 9.sp,
                color = if (passwordTooShort) SignUpColors.Error else SignUpColors.TextSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 2.dp),
            )

            Spacer(Modifier.height(6.dp))

            SignUpField(
                icon = Icons.Filled.Lock,
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Re-enter your password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                isPassword = true,
                passwordVisible = confirmVisible,
                onToggleVisibility = { confirmVisible = !confirmVisible },
                enabled = !isLoading
            )
            Text(
                text = if (mismatch) "Passwords do not match." else "",
                fontSize = 9.sp,
                color = SignUpColors.Error,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .padding(start = 4.dp, top = 2.dp),
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = SignUpColors.FieldBorder)
                Text(
                    text = "OR",
                    fontSize = 9.sp,
                    color = SignUpColors.TextSecondary,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = SignUpColors.FieldBorder)
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SignUpColors.LoginBg)
                    .clickable(enabled = !isLoading) { onLoginClick() }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 11.sp,
                    color = SignUpColors.TextPrimary,
                )
                Text(
                    text = "Login",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SignUpColors.DarkGreen,
                )
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = SignUpColors.DarkGreen,
                    modifier = Modifier.size(14.dp),
                )
            }

            Spacer(Modifier.height(12.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            val isButtonEnabled = formValid && !isLoading
            val buttonColor = if (isButtonEnabled) SignUpColors.DarkGreen else SignUpColors.DarkGreen.copy(alpha = 0.55f)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(buttonColor)
                    .clickable(enabled = isButtonEnabled) {
                        onCreateAccount(
                            if (fullName.isNotBlank()) fullName.trim() else "User",
                            if (email.isNotBlank()) email.trim() else "student@esify.com",
                            password
                        )
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Create Account",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpField(
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
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(if (enabled) Color.White else Color(0xFFF2F5F3))
            .border(1.dp, SignUpColors.FieldBorder, RoundedCornerShape(14.dp))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SignUpColors.ChipBg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SignUpColors.DarkGreen,
                modifier = Modifier.size(20.dp),
            )
        }

        Spacer(Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = SignUpColors.TextPrimary,
            )
            Spacer(Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 12.sp,
                        color = SignUpColors.Hint,
                        maxLines = 1
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    readOnly = !enabled,
                    textStyle = TextStyle(fontSize = 12.sp, color = SignUpColors.TextPrimary),
                    cursorBrush = SolidColor(SignUpColors.DarkGreen),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                    ),
                    visualTransformation = if (isPassword && !passwordVisible) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }

        if (isPassword) {
            Icon(
                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                tint = SignUpColors.TextPrimary,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(enabled = enabled) { onToggleVisibility() },
            )
        }
    }
}

@Composable
private fun SignUpIllustration() {
    if (USE_IMAGE_ILLUSTRATION) {
        Image(
            painter = painterResource(id = R.drawable.ic_signup_illustration),
            contentDescription = null,
            modifier = Modifier.size(width = 100.dp, height = 88.dp),
            contentScale = ContentScale.Fit,
        )
    } else {
        SignUpShapeIllustration()
    }
}

@Composable
private fun SignUpShapeIllustration() {
    Box(
        modifier = Modifier.size(width = 100.dp, height = 88.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(SignUpColors.Wave),
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.School,
                contentDescription = null,
                tint = SignUpColors.DarkGreen,
                modifier = Modifier.size(40.dp),
            )
            SignUpBook(60.dp, 10.dp, SignUpColors.Green)
            Spacer(Modifier.height(2.dp))
            SignUpBook(70.dp, 12.dp, SignUpColors.MidGreen)
            Spacer(Modifier.height(2.dp))
            SignUpBook(64.dp, 10.dp, SignUpColors.DarkGreen)
        }
    }
}

@Composable
private fun SignUpBook(width: Dp, height: Dp, color: Color) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(4.dp))
            .background(color),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview() {
    EsifyTheme { CreateAccountScreen() }
}
