package com.example.esify.navigation

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboarding")
    data object AuthChoice : Screen("auth_choice")
    data object Login : Screen("login")
    data object SignUp : Screen("signup")


    data object Home : Screen("home")
    data object Subjects : Screen("subjects")
    data object Downloads : Screen("downloads")
    data object Profile : Screen("profile")
    data object SubjectDetails : Screen("subject_details/{subjectId}") {
        fun createRoute(subjectId: String): String = "subject_details/$subjectId"
    }
    data object ChapterDetails : Screen("chapter_details/{subjectId}/{chapterId}") {
        fun createRoute(subjectId: String, chapterId: String): String = "chapter_details/$subjectId/$chapterId"
    }
    data object TopicPractice : Screen("topic_practice/{subjectId}/{chapterId}/{topicId}/{initialMode}") {
        fun createRoute(
            subjectId: String,
            chapterId: String,
            topicId: String,
            initialMode: String = "mcq"
        ): String = "topic_practice/$subjectId/$chapterId/$topicId/$initialMode"
    }
}
