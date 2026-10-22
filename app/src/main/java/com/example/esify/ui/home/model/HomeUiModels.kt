package com.example.esify.ui.home.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class SubjectProgressItem(
    val id: String,
    val name: String,
    val completedChapters: Int,
    val totalChapters: Int,
    val progress: Float,
    val percentageText: String,
    val accentColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)

data class QuickActionItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val isHighlighted: Boolean = false,
    val onClick: () -> Unit = {}
)

data class StatItem(
    val value: String,
    val label: String
)
