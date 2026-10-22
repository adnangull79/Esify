package com.example.esify.ui.onboarding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Verified
import androidx.compose.ui.graphics.vector.ImageVector

data class OnboardingFeature(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

data class OnboardingPage(
    val pageNumber: Int,
    val heading: String,
    val description: String,
    val features: List<OnboardingFeature>
)

object OnboardingPageData {
    val pages: List<OnboardingPage> = listOf(
        OnboardingPage(
            pageNumber = 1,
            heading = "Master Class 9 Boards",
            description = "Your all-in-one companion for Matric exam preparation with curriculum-aligned study materials.",
            features = listOf(
                OnboardingFeature(
                    title = "Sindh & Federal Boards",
                    subtitle = "Targeted board syllabus",
                    icon = Icons.Filled.AccountBalance
                ),
                OnboardingFeature(
                    title = "All 4 Core Subjects",
                    subtitle = "CS, Physics, Chem & Bio",
                    icon = Icons.Filled.AutoStories
                ),
                OnboardingFeature(
                    title = "Offline Access",
                    subtitle = "Download & study anywhere",
                    icon = Icons.Filled.Download
                ),
                OnboardingFeature(
                    title = "100% Free Prep",
                    subtitle = "No subscriptions required",
                    icon = Icons.Filled.Verified
                )
            )
        ),
        OnboardingPage(
            pageNumber = 2,
            heading = "Learn Better, Score Higher",
            description = "Study with structured concepts, textbook solutions, and authentic board exam material.",
            features = listOf(
                OnboardingFeature(
                    title = "Chapter-wise Notes",
                    subtitle = "Clear & concise concepts",
                    icon = Icons.AutoMirrored.Filled.MenuBook
                ),
                OnboardingFeature(
                    title = "Practice MCQs",
                    subtitle = "Instant answers & logic",
                    icon = Icons.Filled.Quiz
                ),
                OnboardingFeature(
                    title = "Solved Exercises",
                    subtitle = "Textbook questions solved",
                    icon = Icons.Filled.AssignmentTurnedIn
                ),
                OnboardingFeature(
                    title = "Past Papers",
                    subtitle = "Board exam patterns",
                    icon = Icons.Filled.HistoryEdu
                )
            )
        ),
        OnboardingPage(
            pageNumber = 3,
            heading = "Practice Smart with MCQs",
            description = "Test your exam readiness with interactive quizzes and real-time performance analytics.",
            features = listOf(
                OnboardingFeature(
                    title = "Custom Quizzes",
                    subtitle = "Practice topic by topic",
                    icon = Icons.Filled.Tune
                ),
                OnboardingFeature(
                    title = "New Questions Every Time",
                    subtitle = "Varied question sets",
                    icon = Icons.Filled.Shuffle
                ),
                OnboardingFeature(
                    title = "Track Your Progress",
                    subtitle = "Detailed performance stats",
                    icon = Icons.AutoMirrored.Filled.TrendingUp
                ),
                OnboardingFeature(
                    title = "Exam Ready",
                    subtitle = "Boost exam confidence",
                    icon = Icons.Filled.EmojiEvents
                )
            )
        )
    )
}
