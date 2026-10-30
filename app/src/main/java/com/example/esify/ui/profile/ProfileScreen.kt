package com.example.esify.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.profile.components.LogoutButton
import com.example.esify.ui.profile.components.ProfileHeader
import com.example.esify.ui.profile.components.ProfileMenuCard
import com.example.esify.ui.profile.components.StudentProfileCard
import com.example.esify.ui.profile.model.ProfileMenuItem
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.LogoutRed
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToDownloads: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onClassAndBoardClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    onQuizHistoryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    val accountMenuItems = remember(onClassAndBoardClick, onProgressClick, onNavigateToDownloads, onQuizHistoryClick) {
        listOf(
            ProfileMenuItem(
                id = "class_board",
                title = "Class and Board",
                subtitle = "Class 9 • Board not selected",
                icon = Icons.Filled.School,
                onClick = onClassAndBoardClick
            ),
            ProfileMenuItem(
                id = "progress",
                title = "My Progress",
                subtitle = "View your detailed progress",
                icon = Icons.Filled.BarChart,
                onClick = onProgressClick
            ),
            ProfileMenuItem(
                id = "downloads",
                title = "Downloaded Content",
                subtitle = "Manage your offline downloads",
                icon = Icons.Filled.Download,
                onClick = onNavigateToDownloads
            ),
            ProfileMenuItem(
                id = "quiz_history",
                title = "My Quiz History",
                subtitle = "View all your completed quizzes",
                icon = Icons.Filled.BarChart,
                onClick = onQuizHistoryClick
            )
        )
    }

    val settingsMenuItems = remember(onSettingsClick, onHelpClick, onAboutClick) {
        listOf(
            ProfileMenuItem(
                id = "settings",
                title = "Settings",
                subtitle = "Theme, notifications and more",
                icon = Icons.Filled.Settings,
                onClick = onSettingsClick
            ),
            ProfileMenuItem(
                id = "help",
                title = "Help & Support",
                subtitle = "FAQs, contact us",
                icon = Icons.AutoMirrored.Filled.HelpOutline,
                onClick = onHelpClick
            ),
            ProfileMenuItem(
                id = "about",
                title = "About Esify",
                subtitle = "Version 1.0.0",
                icon = Icons.Filled.Info,
                onClick = onAboutClick
            )
        )
    }

    val currentUser = remember { com.example.esify.data.auth.AuthRepository().currentUser }
    val studentName = currentUser?.displayName?.ifBlank { "Student" } ?: "Student"
    val studentEmail = currentUser?.email?.ifBlank { "student@esify.com" } ?: "student@esify.com"

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(
                    text = "Log Out",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = AuthDarkGreen
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to log out of your Esify account?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryText
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogoutClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LogoutRed,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Log Out", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false }
                ) {
                    Text(text = "Cancel", color = SecondaryText)
                }
            },
            containerColor = WhiteCards,
            shape = RoundedCornerShape(18.dp)
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Forest-green Profile Header
        ProfileHeader()

        // 2. Student Profile Card (overlaps header)
        StudentProfileCard(
            modifier = Modifier.offset(y = (-20).dp),
            displayName = studentName,
            email = studentEmail,
            classAndBoardText = "Class 9 • Board not selected",
            onEditProfileClick = onEditProfileClick,
            onClassAndBoardClick = onClassAndBoardClick
        )

        // 3. Account and Learning Menu
        ProfileMenuCard(
            items = accountMenuItems,
            modifier = Modifier.offset(y = (-8).dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 4. Settings and Support Menu
        ProfileMenuCard(
            items = settingsMenuItems
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 5. Logout Button
        LogoutButton(
            onClick = { showLogoutDialog = true }
        )

        // Bottom breathing room above the navigation bar
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EsifyTheme {
        ProfileScreen()
    }
}
