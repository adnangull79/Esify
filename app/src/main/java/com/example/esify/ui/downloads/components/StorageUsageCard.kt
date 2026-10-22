package com.example.esify.ui.downloads.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.ui.downloads.model.StorageUsage
import com.example.esify.ui.home.components.CircularProgress
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.ProgressTrack
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun StorageUsageCard(
    modifier: Modifier = Modifier,
    storageUsage: StorageUsage = StorageUsage()
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, OutlineDivider),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Circular progress & storage text
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgress(
                    progress = storageUsage.progress,
                    percentageText = storageUsage.percentageText,
                    size = 68.dp,
                    strokeWidth = 7.dp,
                    color = PrimaryGreen,
                    trackColor = ProgressTrack
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Storage Used",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${storageUsage.usedStorage} of ${storageUsage.totalStorage}",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                }
            }

            HorizontalDivider(
                color = OutlineDivider,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            // Statistics row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StorageStatItem(
                    value = storageUsage.chaptersCount,
                    label = "Chapters",
                    modifier = Modifier.weight(1f)
                )

                VerticalDivider(
                    color = OutlineDivider,
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .width(1.dp)
                )

                StorageStatItem(
                    value = storageUsage.mcqBanksCount,
                    label = "MCQ Banks",
                    modifier = Modifier.weight(1f)
                )

                VerticalDivider(
                    color = OutlineDivider,
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .width(1.dp)
                )

                StorageStatItem(
                    value = storageUsage.keyBooksCount,
                    label = "Key Books",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StorageStatItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = SecondaryText
        )
    }
}
