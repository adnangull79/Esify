package com.example.esify.ui.chapterdetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.ChapterItem
import com.example.esify.ui.home.components.CircularProgress
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.ProgressTrack
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ChapterProgressCard(
    chapter: ChapterItem,
    accentColor: Color,
    modifier: Modifier = Modifier
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgress(
                    progress = (chapter.progressPercent / 100f).coerceIn(0f, 1f),
                    percentageText = "${chapter.progressPercent}%",
                    size = 64.dp,
                    strokeWidth = 6.dp,
                    color = accentColor,
                    trackColor = ProgressTrack
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Chapter Progress",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${chapter.completedTopics} of ${chapter.totalTopicsCount} topics completed",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(
                progress = { (chapter.progressPercent / 100f).coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = accentColor,
                trackColor = ProgressTrack,
                strokeCap = StrokeCap.Round
            )
        }
    }
}
