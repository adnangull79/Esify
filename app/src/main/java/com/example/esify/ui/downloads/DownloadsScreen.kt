package com.example.esify.ui.downloads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.downloads.components.AvailableDownloadSection
import com.example.esify.ui.downloads.components.CurrentlyDownloadingSection
import com.example.esify.ui.downloads.components.DownloadedContentSection
import com.example.esify.ui.downloads.components.DownloadsHeader
import com.example.esify.ui.downloads.components.FilterChipsRow
import com.example.esify.ui.downloads.components.StorageUsageCard
import com.example.esify.ui.theme.EsifyTheme

@Composable
fun DownloadsScreen(
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("All (25)") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Forest-green header with Downloads title and offline copy
        DownloadsHeader()

        // 2. Storage usage summary card
        StorageUsageCard(
            modifier = Modifier.offset(y = (-20).dp)
        )

        // 3. Horizontally scrollable content filter chips
        FilterChipsRow(
            selectedFilter = selectedFilter,
            onFilterSelected = { selectedFilter = it },
            modifier = Modifier.offset(y = (-8).dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // 4. Currently downloading section
        CurrentlyDownloadingSection(
            onPauseAllClick = { /* Static preview as specified in requirements */ }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 5. Downloaded content section
        DownloadedContentSection(
            onManageClick = { /* Static preview */ },
            onItemClick = { /* Static preview */ }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 6. Available for download section
        AvailableDownloadSection(
            onViewAllClick = { /* Static preview */ },
            onDownloadClick = { /* Static preview */ }
        )

        // Bottom breathing room above the navigation bar
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DownloadsScreenPreview() {
    EsifyTheme {
        DownloadsScreen()
    }
}
