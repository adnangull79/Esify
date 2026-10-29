package com.example.esify.ui.auth

import android.util.Patterns
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
import androidx.compose.runtime.withFrameNanos
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.R
import com.example.esify.ui.theme.EsifyTheme
import kotlinx.coroutines.delay

// ---------------------------------------------------------------------------
// Debug switches. Change only if the crash comes back (see notes).
// ---------------------------------------------------------------------------

/** true = use the drawable image; false = use the simple shape illustration. */
private const val USE_IMAGE_ILLUSTRATION = true

/** false = keyboard may cover fields; true = adds imePadding to the scroll column. */
private const val ENABLE_IME_PADDING = false

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

// ---------------------------------------------------------------------------
// Screen (outer shell: cheap background first, heavy content after the
// navigation transition has finished)
// ---------------------------------------------------------------------------

@Composable
fun CreateAccountScreen(
    onBack: () -> Unit = {},
    onCreateAccount: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    deferContent: Boolean = true,
) {
    var contentReady by remember { mutableStateOf(!deferContent) }

    LaunchedEffect(Unit) {
        if (!contentReady) {
            withFrameNanos { }   // wait for the first frame
            delay(400)           // let the navigation transition finish
            contentReady = true
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
        // Decorative wave at the bottom (cheap, always present)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(topStart = 220.dp, topEnd = 220.dp))
                .background(SignUpColors.Wave),
        )

        if (contentReady) {
            SignUpForm(
                onBack = onBack,
                onCreateAccount = onCreateAccount,
                onLoginClick = onLoginClick,
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Form (scrolling content)
// ---------------------------------------------------------------------------

@Composable
private fun SignUpForm(
    onBack: () -> Unit,
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
            .then(if (ENABLE_IME_PADDING) Modifier.imePadding() else Modifier)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp),
    ) {
        // Back arrow
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = SignUpColors.DarkGreen,
            modifier = Modifier
                .size(26.dp)
                .clickable { onBack() },
        )

        Spacer(Modifier.height(6.dp))

        // Header: title + subtitle on the left, illustration on the right
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Create\nAccount",
                    fontSize = 30.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SignUpColors.DarkGreen,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Join Esify and start your exam\npreparation journey today.",
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    color = SignUpColors.TextSecondary,
                )
            }
            SignUpIllustration()
        }

        Spacer(Modifier.height(14.dp))

        SignUpField(
            icon = Icons.Filled.Person,
            label = "Full Name",
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Enter your full name",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        Spacer(Modifier.height(10.dp))

        SignUpField(
            icon = Icons.Filled.Email,
            label = "Email Address",
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter your email address",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        )

        Spacer(Modifier.height(10.dp))

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
        )
        Text(
            text = "Password must be at least 6 characters long.",
            fontSize = 9.sp,
            color = if (passwordTooShort) SignUpColors.Error else SignUpColors.TextSecondary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 4.dp),
        )

        Spacer(Modifier.height(8.dp))

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
        )
        // Always in the layout (never added/removed), so the Column never changes shape
        Text(
            text = if (mismatch) "Passwords do not match." else "",
            fontSize = 9.sp,
            color = SignUpColors.Error,
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(start = 4.dp, top = 4.dp),
        )

        Spacer(Modifier.height(14.dp))

        // Create Account button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(SignUpColors.DarkGreen)
                .alpha(if (formValid) 1f else 0.85f)
                .clickable {
                    onCreateAccount(
                        if (fullName.isNotBlank()) fullName.trim() else "User",
                        if (email.isNotBlank()) email.trim() else "student@esify.com",
                        password
                    )
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
                modifier = Modifier.size(20.dp),
            )
        }

        Spacer(Modifier.height(12.dp))

        // OR divider
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

        Spacer(Modifier.height(12.dp))

        // Login row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SignUpColors.LoginBg)
                .clickable { onLoginClick() }
                .padding(vertical = 15.dp),
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
                modifier = Modifier.size(15.dp),
            )
        }

        // Space so the last row isn't hidden behind the decorative wave
        Spacer(Modifier.height(48.dp))
    }
}

// ---------------------------------------------------------------------------
// Input field styled like the design
// ---------------------------------------------------------------------------

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
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, SignUpColors.FieldBorder, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SignUpColors.ChipBg),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SignUpColors.DarkGreen,
                modifier = Modifier.size(22.dp),
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                color = SignUpColors.TextPrimary,
            )
            Spacer(Modifier.height(4.dp))
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 12.sp,
                        color = SignUpColors.Hint,
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
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
private fun SignUpIllustration() {
    if (USE_IMAGE_ILLUSTRATION) {
        Image(
            painter = painterResource(id = R.drawable.ic_signup_illustration),
            contentDescription = null,
            modifier = Modifier.size(width = 150.dp, height = 135.dp),
            contentScale = ContentScale.Fit,
        )
    } else {
        SignUpShapeIllustration()
    }
}

/** Fallback made only from icons and boxes (no image decoding). */
@Composable
private fun SignUpShapeIllustration() {
    Box(
        modifier = Modifier.size(width = 150.dp, height = 135.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(SignUpColors.Wave),
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.School,
                contentDescription = null,
                tint = SignUpColors.DarkGreen,
                modifier = Modifier.size(58.dp),
            )
            SignUpBook(80.dp, 14.dp, SignUpColors.Green)
            Spacer(Modifier.height(2.dp))
            SignUpBook(92.dp, 16.dp, SignUpColors.MidGreen)
            Spacer(Modifier.height(2.dp))
            SignUpBook(84.dp, 14.dp, SignUpColors.DarkGreen)
        }
    }
}

@Composable
private fun SignUpBook(width: Dp, height: Dp, color: Color) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(6.dp))
            .background(color),
    )
}

// ---------------------------------------------------------------------------
// Preview (deferContent = false so the form shows up in the preview pane)
// ---------------------------------------------------------------------------

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview() {
    EsifyTheme { CreateAccountScreen(deferContent = false) }
}