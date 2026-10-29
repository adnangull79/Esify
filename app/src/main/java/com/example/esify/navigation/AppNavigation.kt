package com.example.esify.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.esify.data.preferences.OnboardingPreferences
import com.example.esify.data.preferences.rememberOnboardingPreferences
import com.example.esify.ui.auth.AuthChoiceScreen
import com.example.esify.ui.auth.CreateAccountScreen
import com.example.esify.ui.auth.placeholder.LoginPlaceholderScreen
import com.example.esify.ui.chapterdetails.ChapterDetailsScreen
import com.example.esify.ui.downloads.DownloadsScreen
import com.example.esify.ui.home.HomeScreen
import com.example.esify.ui.onboarding.OnboardingScreen
import com.example.esify.ui.onboarding.OnboardingSplashScreen
import com.example.esify.ui.profile.ProfileScreen
import com.example.esify.ui.subjectdetails.SubjectDetailsScreen
import com.example.esify.ui.subjects.SubjectsScreen
import com.example.esify.ui.topicpractice.TopicPracticeScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    preferences: OnboardingPreferences? = null,
    initialOnboardingCompleted: Boolean? = null,
    isAuthenticated: Boolean = false
) {
    val prefs = preferences ?: rememberOnboardingPreferences()
    var isOnboardingCompleted by remember { mutableStateOf(initialOnboardingCompleted) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (isOnboardingCompleted == null) {
            isOnboardingCompleted = prefs.isOnboardingCompleted()
        }
    }

    if (isOnboardingCompleted == null) {
        OnboardingSplashScreen(modifier = modifier)
    } else {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val hideBottomBarRoutes = setOf(
            Screen.Onboarding.route,
            Screen.AuthChoice.route,
            Screen.Login.route,
            Screen.SignUp.route
        )

        val showBottomBar = if (currentRoute == null) {
            isOnboardingCompleted == true && isAuthenticated
        } else {
            currentRoute !in hideBottomBarRoutes
        }

        val startDestinationRoute = when {
            isOnboardingCompleted != true -> Screen.Onboarding.route
            !isAuthenticated -> Screen.AuthChoice.route
            else -> Screen.Home.route
        }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (showBottomBar) {
                    EsifyBottomBar(navController = navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestinationRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Onboarding.route) {
                    OnboardingScreen(
                        onCompleteOnboarding = {
                            coroutineScope.launch {
                                prefs.setOnboardingCompleted(true)
                            }
                            navController.navigate(Screen.AuthChoice.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
                composable(Screen.AuthChoice.route) {
                    AuthChoiceScreen(
                        onLoginClick = {
                            if (navController.currentDestination?.route == Screen.AuthChoice.route) {
                                navController.navigate(Screen.Login.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                        onSignUpClick = {
                            if (navController.currentDestination?.route == Screen.AuthChoice.route) {
                                navController.navigate(Screen.SignUp.route) {
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
                composable(Screen.Login.route) {
                    LoginPlaceholderScreen(
                        onBackClick = {
                            if (navController.currentDestination?.route == Screen.Login.route) {
                                navController.popBackStack()
                            }
                        },
                        onContinueToHomeClick = {
                            if (navController.currentDestination?.route == Screen.Login.route) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.AuthChoice.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
                composable(Screen.SignUp.route) {
                    CreateAccountScreen(
                        onBack = {
                            if (navController.currentDestination?.route == Screen.SignUp.route) {
                                navController.popBackStack()
                            }
                        },
                        onLoginClick = {
                            if (navController.currentDestination?.route == Screen.SignUp.route) {
                                navController.navigate(Screen.Login.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                        onCreateAccount = { _, _, _ ->
                            if (navController.currentDestination?.route == Screen.SignUp.route) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.AuthChoice.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
                composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToSubjects = {
                        navController.navigate(Screen.Subjects.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onNavigateToDownloads = {
                        navController.navigate(Screen.Downloads.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onSubjectClick = { subjectId ->
                        navController.navigate(Screen.SubjectDetails.createRoute(subjectId))
                    }
                )
            }
            composable(Screen.Subjects.route) {
                SubjectsScreen(
                    onSubjectClick = { subjectId ->
                        navController.navigate(Screen.SubjectDetails.createRoute(subjectId))
                    }
                )
            }
            composable(Screen.Downloads.route) {
                DownloadsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigateToDownloads = {
                        navController.navigate(Screen.Downloads.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(
                route = Screen.SubjectDetails.route,
                arguments = listOf(
                    navArgument("subjectId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val subjectId = backStackEntry.arguments?.getString("subjectId")
                SubjectDetailsScreen(
                    subjectId = subjectId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onChapterClick = { subId, chId ->
                        navController.navigate(Screen.ChapterDetails.createRoute(subId, chId))
                    }
                )
            }
            composable(
                route = Screen.ChapterDetails.route,
                arguments = listOf(
                    navArgument("subjectId") {
                        type = NavType.StringType
                    },
                    navArgument("chapterId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val subjectId = backStackEntry.arguments?.getString("subjectId")
                val chapterId = backStackEntry.arguments?.getString("chapterId")
                ChapterDetailsScreen(
                    subjectId = subjectId,
                    chapterId = chapterId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onTopicMcqsClick = { subId, chId, tId ->
                        navController.navigate(Screen.TopicPractice.createRoute(subId, chId, tId, "mcq"))
                    },
                    onTopicQuestionsClick = { subId, chId, tId ->
                        navController.navigate(Screen.TopicPractice.createRoute(subId, chId, tId, "short_questions"))
                    },
                    onTopicQuizClick = { subId, chId, tId ->
                        navController.navigate(Screen.TopicPractice.createRoute(subId, chId, tId, "quiz"))
                    }
                )
            }
            composable(
                route = Screen.TopicPractice.route,
                arguments = listOf(
                    navArgument("subjectId") {
                        type = NavType.StringType
                    },
                    navArgument("chapterId") {
                        type = NavType.StringType
                    },
                    navArgument("topicId") {
                        type = NavType.StringType
                    },
                    navArgument("initialMode") {
                        type = NavType.StringType
                        defaultValue = "mcq"
                    }
                )
            ) { backStackEntry ->
                val subjectId = backStackEntry.arguments?.getString("subjectId")
                val chapterId = backStackEntry.arguments?.getString("chapterId")
                val topicId = backStackEntry.arguments?.getString("topicId")
                val initialMode = backStackEntry.arguments?.getString("initialMode")
                TopicPracticeScreen(
                    subjectId = subjectId,
                    chapterId = chapterId,
                    topicId = topicId,
                    initialModeString = initialMode,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
}
