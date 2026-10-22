package com.example.esify.ui.downloads.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.downloads.model.DownloadedItem
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightLeafGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.LightPurple
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.PurpleAccent
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

val DefaultDownloadedItems = listOf(
    DownloadedItem(
        id = "cs_ch1",
        title = "Computer Science - Chapter 1",
        subtitle = "Key Book + Notes + MCQs",
        filesCount = 3,
        sizeText = "120 MB",
        dateText = "12 Oct 2024",
        accentColor = PrimaryGreen,
        backgroundColor = LightMint,
        icon = Icons.Filled.Computer
    ),
    DownloadedItem(
        id = "chem_ch2",
        title = "Chemistry - Chapter 2",
        subtitle = "Notes + Solved Exercises",
        filesCount = 2,
        sizeText = "85 MB",
        dateText = "10 Oct 2024",
        accentColor = OrangeAccent,
        backgroundColor = LightOrange,
        icon = Icons.Filled.Science
    ),
    DownloadedItem(
        id = "phys_ch1",
        title = "Physics - Chapter 1",
        subtitle = "Key Book + MCQs",
        filesCount = 3,
        sizeText = "110 MB",
        dateText = "8 Oct 2024",
        accentColor = PurpleAccent,
        backgroundColor = LightPurple,
        icon = Icons.Filled.Bolt
    ),
    DownloadedItem(
        id = "bio_ch1",
        title = "Biology - Chapter 1",
        subtitle = "Notes + MCQs + Solved",
        filesCount = 4,
        sizeText = "95 MB",
        dateText = "5 Oct 2024",
        accentColor = LeafGreen,
        backgroundColor = LightLeafGreen,
        icon = Icons.Filled.Spa
    )
)

@Composable
fun DownloadedContentSection(
    modifier: Modifier = Modifier,
    items: List<DownloadedItem> = DefaultDownloadedItems,
    onManageClick: () -> Unit = {},
    onItemClick: (DownloadedItem) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Downloaded Content",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Text(
                text = "Manage",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryGreen,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = onManageClick
                    )
                    .padding(vertical = 4.dp, horizontal = 4.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                DownloadedContentCard(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun DownloadedContentCard(
    item: DownloadedItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, OutlineDivider),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(item.backgroundColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = item.accentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText,
                        maxLines = 1
                    )
                }

                // Overflow menu icon
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = SecondaryText,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Metadata row: Files, size, date & Downloaded chip
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.filesCount} Files • ${item.sizeText} • ${item.dateText}",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryText
                )

                // Downloaded status chip
                Row(
                    modifier = Modifier
                        .background(LightMint, shape = RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Downloaded",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryGreen
                    )
                }
            }
        }
    }
}
