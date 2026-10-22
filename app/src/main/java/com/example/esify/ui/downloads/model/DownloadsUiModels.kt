package com.example.esify.ui.downloads.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class StorageUsage(
    val progress: Float = 0.62f,
    val percentageText: String = "62%",
    val usedStorage: String = "3.1 GB",
    val totalStorage: String = "5.0 GB",
    val chaptersCount: String = "48",
    val mcqBanksCount: String = "12",
    val keyBooksCount: String = "8"
)

data class DownloadingItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val progress: Float,
    val percentageText: String,
    val accentColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)

data class DownloadedItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val filesCount: Int,
    val sizeText: String,
    val dateText: String,
    val accentColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)

data class AvailableDownloadItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val filesCount: Int,
    val sizeText: String,
    val accentColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)
