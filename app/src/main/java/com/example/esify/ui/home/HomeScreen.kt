package com.example.esify.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.home.components.ContinueLearningSection
import com.example.esify.ui.home.components.CreateCustomQuizCard
import com.example.esify.ui.home.components.HomeHeader
import com.example.esify.ui.home.components.OverallProgressCard
import com.example.esify.ui.home.components.QuickActionsSection
import com.example.esify.ui.home.components.RecentActivitySection
import com.example.esify.ui.home.components.SubjectsSection
import com.example.esify.ui.theme.EsifyTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToSubjects: () -> Unit = {},
    onNavigateToDownloads: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onSubjectClick: (String) -> Unit = {},
    onNavigateToCreateCustomQuiz: () -> Unit = {},
    onNavigateToQuizHistory: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Branded Header
        HomeHeader(
            onNotificationClick = onNotificationClick
        )

        // 2. Overall Progress Card (following the header closely)
        OverallProgressCard(
            modifier = Modifier.offset(y = (-20).dp),
            onViewDetailsClick = onNavigateToSubjects
        )

        // 3. Your Subjects Grid
        SubjectsSection(
            modifier = Modifier.offset(y = (-8).dp),
            onSeeAllClick = onNavigateToSubjects,
            onSubjectClick = { subject -> onSubjectClick(subject.id) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 3b. Create Custom Quiz Card Button
        CreateCustomQuizCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = onNavigateToCreateCustomQuiz
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4. Continue Learning Section
        ContinueLearningSection(
            onSeeAllClick = onNavigateToSubjects,
            onContinueClick = { /* Placeholder action for demo */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Quick Actions Section
        QuickActionsSection(
            onNavigateToDownloads = onNavigateToDownloads,
            onPracticeMcqsClick = { /* Placeholder action */ },
            onReadKeyBookClick = { /* Placeholder action */ },
            onSolvedExercisesClick = { /* Placeholder action */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 6. Recent Activity Section
        RecentActivitySection(
            onSeeAllClick = onNavigateToQuizHistory,
            onActivityClick = { /* Placeholder action */ }
        )

        // Bottom breathing room above the navigation bar
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EsifyTheme {
        HomeScreen()
    }
}
