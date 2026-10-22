package com.example.esify.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.ui.theme.DarkForestGreen
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.ProgressTrack
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ContinueLearningSection(
    modifier: Modifier = Modifier,
    subjectName: String = "Computer Science",
    chapterTitle: String = "Chapter 2: Data Representation",
    topicTitle: String = "Topic 1: Number Systems",
    progress: Float = 0.60f,
    percentageText: String = "60%",
    onSeeAllClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = "Continue Learning",
            actionText = "See All",
            onActionClick = onSeeAllClick
        )

        Card(
            modifier = Modifier
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(DarkForestGreen, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Computer,
                            contentDescription = "Computer Science",
                            tint = WhiteCards,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = subjectName,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen
                        )
                        Text(
                            text = chapterTitle,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = topicTitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = SecondaryText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Progress Bar and Percentage
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        progress = { progress.coerceIn(0f, 1f) },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = PrimaryGreen,
                        trackColor = ProgressTrack,
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = percentageText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Continue Button
                Button(
                    onClick = onContinueClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkForestGreen,
                        contentColor = WhiteCards
                    ),
                    contentPadding = PaddingValues(vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Continue",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
