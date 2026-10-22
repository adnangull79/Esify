package com.example.esify.ui.topicpractice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.ChapterItem
import com.example.esify.data.subject.SubjectDetailsItem
import com.example.esify.data.subject.TopicItem
import com.example.esify.ui.theme.DarkForestGreen
import com.example.esify.ui.theme.MidGreen
import com.example.esify.ui.theme.TrophyOrange

@Composable
fun TopicPracticeTopBar(
    subject: SubjectDetailsItem,
    chapter: ChapterItem,
    topic: TopicItem,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onOptionsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(DarkForestGreen, MidGreen)
                )
            )
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${subject.name} • Ch ${chapter.number}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(
                onClick = onBookmarkToggle,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = if (isBookmarked) "Bookmarked" else "Bookmark",
                    tint = if (isBookmarked) TrophyOrange else Color.White.copy(alpha = 0.9f)
                )
            }

            IconButton(
                onClick = onOptionsClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}
