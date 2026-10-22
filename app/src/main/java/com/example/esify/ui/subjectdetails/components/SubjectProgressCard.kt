package com.example.esify.ui.subjectdetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.SubjectDetailsItem
import com.example.esify.ui.home.components.CircularProgress
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.ProgressTrack
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun SubjectProgressCard(
    subject: SubjectDetailsItem,
    modifier: Modifier = Modifier,
    onViewDetailsClick: () -> Unit = {}
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
                    progress = subject.progress,
                    percentageText = subject.percentageText,
                    size = 64.dp,
                    strokeWidth = 6.dp,
                    color = subject.accentColor,
                    trackColor = ProgressTrack
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Subject Progress",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${subject.completedChapters} of ${subject.totalChapters} chapters completed",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = onViewDetailsClick
                        )
                    ) {
                        Text(
                            text = "View Details",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryGreen
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "View Details",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(
                progress = { subject.progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = subject.accentColor,
                trackColor = ProgressTrack,
                strokeCap = StrokeCap.Round
            )
        }
    }
}
