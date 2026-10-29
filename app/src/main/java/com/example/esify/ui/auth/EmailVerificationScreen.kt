package com.example.esify.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.R

private object VerificationColors {
    val DarkGreen = Color(0xFF0B4A33)
    val BgTop = Color(0xFFF4FAF6)
    val BgBottom = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF14211B)
    val TextSecondary = Color(0xFF5F6D66)
    val LightGreenBg = Color(0xFFE6F3EB)
    val Wave = Color(0xFFE9F5EE)
    val InfoBadgeBg = Color(0xFF63E6A6)
}

@Composable
fun EmailVerificationScreen(
    email: String = "student@esify.com",
    onBackClick: () -> Unit = {},
    onEditEmailClick: () -> Unit = {},
    onBackToLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val displayEmail = email.ifBlank { "student@esify.com" }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(VerificationColors.BgTop, VerificationColors.BgBottom))),
    ) {
        // Decorative wave at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(topStart = 240.dp, topEnd = 240.dp))
                .background(VerificationColors.Wave),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Back Arrow
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = VerificationColors.DarkGreen,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onBackClick() },
                )
            }

            Spacer(Modifier.height(4.dp))

            // Header Illustration
            VerificationIllustration()

            Spacer(Modifier.height(16.dp))

            // Title
            Text(
                text = "Check Your Email",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = VerificationColors.DarkGreen,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "We have sent a verification link to your email\naddress. Please open your email and click on\nthe link to verify your account.",
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = VerificationColors.TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(14.dp))

            // Email Address Box with Edit Pencil Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerificationColors.LightGreenBg)
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = null,
                        tint = VerificationColors.DarkGreen,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = displayEmail,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerificationColors.TextPrimary
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Email",
                    tint = VerificationColors.DarkGreen,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { onEditEmailClick() }
                )
            }

            Spacer(Modifier.height(16.dp))

            // Important Information Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(VerificationColors.LightGreenBg)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Info Header
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(VerificationColors.InfoBadgeBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            tint = VerificationColors.DarkGreen,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Important",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = VerificationColors.DarkGreen
                    )
                }

                // Instruction Item 1: Inbox
                ImportantItem(
                    icon = Icons.Outlined.Email,
                    text = buildAnnotatedString {
                        append("Check your inbox for an email from ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = VerificationColors.DarkGreen)) {
                            append("Esify")
                        }
                        append(".")
                    }
                )

                // Instruction Item 2: Spam / Junk
                ImportantItem(
                    icon = Icons.Outlined.Shield,
                    text = buildAnnotatedString {
                        append("Also check your ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = VerificationColors.DarkGreen)) {
                            append("Spam or Junk")
                        }
                        append(" folder if you don't see the email.")
                    }
                )

                // Instruction Item 3: Link Click
                ImportantItem(
                    icon = Icons.Filled.Link,
                    text = buildAnnotatedString {
                        append("Click on the verification link in the email to activate your account.")
                    }
                )
            }

            Spacer(Modifier.height(24.dp))

            // Primary Button: Back to Login
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(VerificationColors.DarkGreen)
                    .clickable { onBackToLoginClick() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Back to Login",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ImportantItem(
    icon: ImageVector,
    text: androidx.compose.ui.text.AnnotatedString
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = VerificationColors.DarkGreen,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            lineHeight = 15.sp,
            color = VerificationColors.TextSecondary
        )
    }
}

/**
 * Header Illustration
 */
@Composable
private fun VerificationIllustration() {
    Image(
        painter = painterResource(id = R.drawable.ic_signup_illustration),
        contentDescription = "Check Email Illustration",
        modifier = Modifier.size(width = 160.dp, height = 145.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun EmailVerificationScreenPreview() {
    MaterialTheme {
        EmailVerificationScreen()
    }
}
