package com.example.esify.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.auth.components.AuthChoiceCard
import com.example.esify.ui.auth.components.AuthIllustration
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.AuthSecondaryText
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.LightBackground
import com.example.esify.ui.theme.MintAccent
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun AuthChoiceScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // 1. Centered graduation-cap and stacked-books illustration with floating badges
            AuthIllustration()

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Large, bold, dark-green Esify brand title
            Text(
                text = "Esify",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 34.sp),
                fontWeight = FontWeight.ExtraBold,
                color = AuthDarkGreen
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Heading
            Text(
                text = "Your Complete Board Exam Preparation App",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 17.sp),
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 4. Supporting text
            Text(
                text = "Login or create a new account to start your learning journey.",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = AuthSecondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 5. Login Card
            AuthChoiceCard(
                title = "Login",
                supportingText = "Welcome back! Login to continue your learning.",
                leadingIcon = Icons.Filled.Person,
                iconTint = AuthDarkGreen,
                buttonText = "Login",
                buttonContainerColor = AuthDarkGreen,
                buttonContentColor = WhiteCards,
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 6. Sign Up Card
            AuthChoiceCard(
                title = "Sign Up",
                supportingText = "Create a new account and start your exam preparation journey.",
                leadingIcon = Icons.Filled.PersonAdd,
                iconTint = PrimaryGreen,
                buttonText = "Create Account",
                buttonContainerColor = MintAccent,
                buttonContentColor = AuthDarkGreen,
                onClick = onSignUpClick
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 7. Security footer
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Security,
                    contentDescription = null,
                    tint = AuthSecondaryText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Your data is safe and secure",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = AuthSecondaryText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthChoiceScreenPreview() {
    EsifyTheme {
        AuthChoiceScreen(
            onLoginClick = {},
            onSignUpClick = {}
        )
    }
}
