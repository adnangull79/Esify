package com.example.esify.ui.chapterdetails.components

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
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Quiz
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

data class ActionTileData(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val accentColor: Color,
    val backgroundColor: Color,
    val onClick: () -> Unit = {}
)

@Composable
fun ChapterActionTiles(
    modifier: Modifier = Modifier,
    onMcqsClick: () -> Unit = {},
    onShortQuestionsClick: () -> Unit = {},
    onQuizClick: () -> Unit = {},
    onDownloadClick: () -> Unit = {}
) {
    val tiles = remember(onMcqsClick, onShortQuestionsClick, onQuizClick, onDownloadClick) {
        listOf(
            ActionTileData(
                title = "Chapter MCQs",
                subtitle = "All MCQs",
                icon = Icons.Filled.Quiz,
                accentColor = PurpleAccent,
                backgroundColor = LightPurple,
                onClick = onMcqsClick
            ),
            ActionTileData(
                title = "Chapter Short Questions",
                subtitle = "All Short Questions",
                icon = Icons.AutoMirrored.Filled.HelpOutline,
                accentColor = OrangeAccent,
                backgroundColor = LightOrange,
                onClick = onShortQuestionsClick
            ),
            ActionTileData(
                title = "Chapter Quiz",
                subtitle = "Practice Quiz",
                icon = Icons.Filled.EmojiEvents,
                accentColor = PrimaryGreen,
                backgroundColor = LightMint,
                onClick = onQuizClick
            ),
            ActionTileData(
                title = "Download Chapter",
                subtitle = "Offline Access",
                icon = Icons.Filled.Download,
                accentColor = PrimaryGreen,
                backgroundColor = LightMint,
                onClick = onDownloadClick
            )
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tiles.forEach { tile ->
            Card(
                modifier = Modifier
                    .width(168.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = tile.onClick
                    ),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = WhiteCards),
                border = BorderStroke(1.dp, OutlineDivider),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(tile.backgroundColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = tile.icon,
                                contentDescription = tile.title,
                                tint = tile.accentColor,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = tile.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = tile.subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryText,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
