package com.example.esify.ui.topicpractice.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.data.question.ShortQuestion
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.TrophyOrange
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ShortQuestionCard(
    question: ShortQuestion,
    topicTitle: String,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, OutlineDivider),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row: "Short Question" pill badge + Topic title + Bookmark icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(LightOrange)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Short Question",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        fontWeight = FontWeight.Bold,
                        color = OrangeAccent
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = topicTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onBookmarkToggle,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Bookmark Question",
                        tint = if (isBookmarked) TrophyOrange else SecondaryText,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Numbered Question Text
            val formattedQuestion = if (question.question.trimStart().startsWith("${question.questionNumber}.")) {
                question.question
            } else {
                "${question.questionNumber}. ${question.question}"
            }
            Text(
                text = formattedQuestion,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Pale Mint Answer Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(LightMint)
                    .border(BorderStroke(1.dp, PrimaryGreen.copy(alpha = 0.25f)), RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = "Answer Icon",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Answer",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = question.modelAnswer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = PrimaryText
                    )

                    // Numbered answer points when the answer is a list
                    if (question.answerPoints.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            question.answerPoints.forEachIndexed { pointIndex, point ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = "${pointIndex + 1}.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryGreen,
                                        modifier = Modifier.width(22.dp)
                                    )
                                    Text(
                                        text = point,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = PrimaryText,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }

                    if (question.explanation.isNotBlank()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Key Note: ${question.explanation}",
                            style = MaterialTheme.typography.bodySmall,
                            color = SecondaryText
                        )
                    }
                }
            }
        }
    }
}
