package com.example.esify.ui.profile.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.TrophyOrange

data class ProfileStatItem(
    val value: String,
    val label: String,
    val icon: ImageVector,
    val iconTint: Color,
    val backgroundColor: Color
)

data class ProfileMenuItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

val DefaultProfileStats: List<ProfileStatItem> = listOf(
    ProfileStatItem(
        value = "12",
        label = "Chapters\nCompleted",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        iconTint = LeafGreen,
        backgroundColor = LightMint
    ),
    ProfileStatItem(
        value = "86",
        label = "MCQs\nSolved",
        icon = Icons.Filled.TrackChanges,
        iconTint = LeafGreen,
        backgroundColor = LightMint
    ),
    ProfileStatItem(
        value = "5",
        label = "Quizzes\nTaken",
        icon = Icons.Filled.BarChart,
        iconTint = LeafGreen,
        backgroundColor = LightMint
    ),
    ProfileStatItem(
        value = "68%",
        label = "Average\nScore",
        icon = Icons.Filled.EmojiEvents,
        iconTint = TrophyOrange,
        backgroundColor = LightOrange
    )
)

val DefaultAccountMenuItems: List<ProfileMenuItem> = listOf(
    ProfileMenuItem(
        id = "class_board",
        title = "Class and Board",
        subtitle = "Class 9 • Board not selected",
        icon = Icons.Filled.School
    ),
    ProfileMenuItem(
        id = "progress",
        title = "My Progress",
        subtitle = "View your detailed progress",
        icon = Icons.Filled.BarChart
    ),
    ProfileMenuItem(
        id = "downloads",
        title = "Downloaded Content",
        subtitle = "Manage your offline downloads",
        icon = Icons.Filled.Download
    ),
    ProfileMenuItem(
        id = "bookmarks",
        title = "My Bookmarks",
        subtitle = "View your saved topics and questions",
        icon = Icons.Filled.Bookmark
    )
)

val DefaultSettingsMenuItems: List<ProfileMenuItem> = listOf(
    ProfileMenuItem(
        id = "settings",
        title = "Settings",
        subtitle = "Theme, notifications and more",
        icon = Icons.Filled.Settings
    ),
    ProfileMenuItem(
        id = "help",
        title = "Help & Support",
        subtitle = "FAQs, contact us",
        icon = Icons.AutoMirrored.Filled.HelpOutline
    ),
    ProfileMenuItem(
        id = "about",
        title = "About Esify",
        subtitle = "Version 1.0.0",
        icon = Icons.Filled.Info
    )
)
