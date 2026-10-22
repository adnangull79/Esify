package com.example.esify.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.profile.components.LogoutButton
import com.example.esify.ui.profile.components.ProfileHeader
import com.example.esify.ui.profile.components.ProfileMenuCard
import com.example.esify.ui.profile.components.StudentProfileCard
import com.example.esify.ui.profile.model.ProfileMenuItem
import com.example.esify.ui.theme.EsifyTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToDownloads: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onClassAndBoardClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    onBookmarksClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val accountMenuItems = remember(onClassAndBoardClick, onProgressClick, onNavigateToDownloads, onBookmarksClick) {
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
                id = "bookmarks",
                title = "My Bookmarks",
                subtitle = "View your saved topics and questions",
                icon = Icons.Filled.Bookmark,
                onClick = onBookmarksClick
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
            displayName = "Student",
            email = "student@esify.com",
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

        // 5. Logout Button (non-functional placeholder)
        LogoutButton(
            onClick = onLogoutClick
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
