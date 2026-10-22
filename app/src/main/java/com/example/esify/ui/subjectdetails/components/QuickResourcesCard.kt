package com.example.esify.ui.subjectdetails.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Download
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

private data class QuickResource(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

@Composable
fun QuickResourcesCard(
    modifier: Modifier = Modifier,
    onKeyBookClick: () -> Unit = {},
    onMcqsClick: () -> Unit = {},
    onSolvedClick: () -> Unit = {},
    onDownloadsClick: () -> Unit = {}
) {
    val resources = remember(onKeyBookClick, onMcqsClick, onSolvedClick, onDownloadsClick) {
        listOf(
            QuickResource("Key Book", "Study Notes", Icons.AutoMirrored.Filled.MenuBook, onKeyBookClick),
            QuickResource("MCQs", "Practice", Icons.Filled.Quiz, onMcqsClick),
            QuickResource("Solved", "Exercises", Icons.Filled.AssignmentTurnedIn, onSolvedClick),
            QuickResource("Downloads", "Offline Access", Icons.Filled.Download, onDownloadsClick)
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, OutlineDivider),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            resources.forEach { resource ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                            onClick = resource.onClick
                        )
                        .padding(vertical = 4.dp, horizontal = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(LightMint, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = resource.icon,
                            contentDescription = resource.title,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = resource.title,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = resource.subtitle,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        ),
                        color = SecondaryText,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
