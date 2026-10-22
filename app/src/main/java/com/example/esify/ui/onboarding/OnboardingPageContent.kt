package com.example.esify.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.onboarding.components.FeatureGrid
import com.example.esify.ui.onboarding.components.OnboardingIllustration
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Page illustration (Compose shapes & icons)
        OnboardingIllustration(
            pageNumber = page.pageNumber
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Heading
        Text(
            text = page.heading,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp),
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Supporting description
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp),
            color = SecondaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.95f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 2x2 Feature Cards Grid
        FeatureGrid(
            features = page.features,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
