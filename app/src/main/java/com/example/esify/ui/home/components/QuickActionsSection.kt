package com.example.esify.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.ripple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.home.model.QuickActionItem
import com.example.esify.ui.theme.DarkForestGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun QuickActionsSection(
    modifier: Modifier = Modifier,
    onNavigateToDownloads: () -> Unit = {},
    onPracticeMcqsClick: () -> Unit = {},
    onReadKeyBookClick: () -> Unit = {},
    onSolvedExercisesClick: () -> Unit = {}
) {
    val quickActions = listOf(
        QuickActionItem(
            id = "mcq",
            title = "Practice MCQs",
            subtitle = "Topic-wise",
            icon = Icons.Filled.Quiz,
            isHighlighted = true,
            onClick = onPracticeMcqsClick
        ),
        QuickActionItem(
            id = "keybook",
            title = "Read Key Book",
            subtitle = "Chapter Notes",
            icon = Icons.Filled.AutoStories,
            isHighlighted = false,
            onClick = onReadKeyBookClick
        ),
        QuickActionItem(
            id = "exercises",
            title = "Solved Exercises",
            subtitle = "Short & Long",
            icon = Icons.Filled.EditNote,
            isHighlighted = false,
            onClick = onSolvedExercisesClick
        ),
        QuickActionItem(
            id = "downloads",
            title = "Downloads",
            subtitle = "Offline Content",
            icon = Icons.Filled.Download,
            isHighlighted = false,
            onClick = onNavigateToDownloads
        )
    )

    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = "Quick Actions",
            actionText = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            quickActions.forEach { action ->
                QuickActionCard(action = action)
            }
        }
    }
}

@Composable
fun QuickActionCard(
    action: QuickActionItem,
    modifier: Modifier = Modifier
) {
    val containerColor = if (action.isHighlighted) DarkForestGreen else WhiteCards
    val iconContainerColor = if (action.isHighlighted) PrimaryGreen.copy(alpha = 0.35f) else LightMint
    val iconColor = if (action.isHighlighted) WhiteCards else PrimaryGreen
    val titleColor = if (action.isHighlighted) WhiteCards else PrimaryText
    val subtitleColor = if (action.isHighlighted) LightMint.copy(alpha = 0.85f) else SecondaryText
    val border = if (action.isHighlighted) null else BorderStroke(1.dp, OutlineDivider)

    Card(
        modifier = modifier
            .width(132.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                onClick = action.onClick
            ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = border,
        elevation = CardDefaults.cardElevation(defaultElevation = if (action.isHighlighted) 2.dp else 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(iconContainerColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = action.icon,
                    contentDescription = action.title,
                    tint = iconColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = action.title,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = titleColor,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = action.subtitle,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = subtitleColor,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}
