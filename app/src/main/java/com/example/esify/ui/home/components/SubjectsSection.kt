package com.example.esify.ui.home.components

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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
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
import com.example.esify.ui.home.model.SubjectProgressItem
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightLeafGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.LightPurple
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.ProgressTrack
import com.example.esify.ui.theme.PurpleAccent
import com.example.esify.ui.theme.SecondaryText

val DefaultSubjects = listOf(
    SubjectProgressItem(
        id = "computer_science",
        name = "Computer Science",
        completedChapters = 8,
        totalChapters = 12,
        progress = 8f / 12f,
        percentageText = "67%",
        accentColor = PrimaryGreen,
        backgroundColor = LightMint,
        icon = Icons.Filled.Computer
    ),
    SubjectProgressItem(
        id = "physics",
        name = "Physics",
        completedChapters = 6,
        totalChapters = 12,
        progress = 6f / 12f,
        percentageText = "50%",
        accentColor = PurpleAccent,
        backgroundColor = LightPurple,
        icon = Icons.Filled.Bolt
    ),
    SubjectProgressItem(
        id = "chemistry",
        name = "Chemistry",
        completedChapters = 5,
        totalChapters = 12,
        progress = 5f / 12f,
        percentageText = "42%",
        accentColor = OrangeAccent,
        backgroundColor = LightOrange,
        icon = Icons.Filled.Science
    ),
    SubjectProgressItem(
        id = "biology",
        name = "Biology",
        completedChapters = 4,
        totalChapters = 12,
        progress = 4f / 12f,
        percentageText = "33%",
        accentColor = LeafGreen,
        backgroundColor = LightLeafGreen,
        icon = Icons.Filled.Spa
    )
)

@Composable
fun SubjectsSection(
    modifier: Modifier = Modifier,
    subjects: List<SubjectProgressItem> = DefaultSubjects,
    onSeeAllClick: () -> Unit = {},
    onSubjectClick: (SubjectProgressItem) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = "Your Subjects",
            actionText = "See All",
            onActionClick = onSeeAllClick
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Display subjects in rows of two
            subjects.chunked(2).forEach { rowSubjects ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowSubjects.forEach { subject ->
                        SubjectCard(
                            subject = subject,
                            modifier = Modifier.weight(1f),
                            onClick = { onSubjectClick(subject) }
                        )
                    }
                    // Handle odd count alignment
                    if (rowSubjects.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectCard(
    subject: SubjectProgressItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            onClick = onClick
        ),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = subject.backgroundColor.copy(alpha = 0.55f)),
        border = BorderStroke(1.dp, subject.accentColor.copy(alpha = 0.22f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(subject.backgroundColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = subject.icon,
                        contentDescription = subject.name,
                        tint = subject.accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = SecondaryText,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = subject.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "${subject.completedChapters}/${subject.totalChapters} Chapters",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = { subject.progress.coerceIn(0f, 1f) },
                    modifier = Modifier
                        .weight(1f)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = subject.accentColor,
                    trackColor = ProgressTrack,
                    strokeCap = StrokeCap.Round
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = subject.percentageText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = subject.accentColor
                )
            }
        }
    }
}
