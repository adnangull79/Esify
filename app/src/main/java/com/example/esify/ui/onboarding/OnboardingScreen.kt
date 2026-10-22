package com.example.esify.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.esify.ui.onboarding.components.OnboardingBottomControls
import com.example.esify.ui.onboarding.components.OnboardingTopBar
import com.example.esify.ui.theme.EsifyTheme

@Composable
fun OnboardingScreen(
    onCompleteOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pages = OnboardingPageData.pages
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Fixed top bar with brand and Skip action
            OnboardingTopBar(
                onSkipClick = onCompleteOnboarding
            )

            // Horizontally swipeable pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { pageIndex ->
                OnboardingPageContent(
                    page = pages[pageIndex]
                )
            }

            // Fixed bottom controls with animated indicators and Get Started button
            OnboardingBottomControls(
                pageCount = pages.size,
                currentPage = pagerState.currentPage,
                onGetStartedClick = onCompleteOnboarding
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    EsifyTheme {
        OnboardingScreen(onCompleteOnboarding = {})
    }
}
