package com.example.esify.ui.topicpractice.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.data.question.McqQuestion
import com.example.esify.data.question.QuestionOption
import com.example.esify.ui.theme.CorrectGreenBackground
import com.example.esify.ui.theme.IncorrectRedBackground
import com.example.esify.ui.theme.LightBackground
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LogoutRed
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.TrophyOrange
import com.example.esify.ui.theme.WhiteCards

@Composable
fun McqCard(
    question: McqQuestion,
    selectedOptionId: String?,
    isRevealed: Boolean,
    isQuizMode: Boolean,
    onOptionSelect: (String) -> Unit,
    onRevealAnswer: () -> Unit,
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row: Type chip and bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(LightMint)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "MCQ",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onBookmarkToggle,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) TrophyOrange else SecondaryText,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Question Text
            Text(
                text = question.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryText
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Options List
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                question.options.forEach { option ->
                    McqOptionRow(
                        option = option,
                        isSelected = selectedOptionId == option.id,
                        isRevealed = isRevealed,
                        isCorrect = option.id == question.correctOptionId,
                        enabled = !isRevealed,
                        onClick = { onOptionSelect(option.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // In Practice Mode: Reveal Button and Explanation Card
            if (!isQuizMode) {
                if (!isRevealed) {
                    Button(
                        onClick = onRevealAnswer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Reveal Answer",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                ExplanationCard(
                    isRevealed = isRevealed,
                    correctAnswerText = "${question.correctOptionId}. ${question.correctOption.text}",
                    explanation = question.explanation
                )
            }
        }
    }
}

@Composable
private fun McqOptionRow(
    option: QuestionOption,
    isSelected: Boolean,
    isRevealed: Boolean,
    isCorrect: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val (bgColor, borderColor, statusIcon, iconTint) = when {
        !isRevealed -> {
            if (isSelected) {
                listOf(LightMint, PrimaryGreen, null, null)
            } else {
                listOf(LightBackground, OutlineDivider, null, null)
            }
        }
        isCorrect -> {
            // Correct option gets pale-green bg, green border, checkmark icon
            listOf(CorrectGreenBackground, PrimaryGreen, Icons.Filled.Check, PrimaryGreen)
        }
        isSelected -> {
            // Selected incorrect option gets pale-red bg, red border, cross icon
            listOf(IncorrectRedBackground, LogoutRed, Icons.Filled.Close, LogoutRed)
        }
        else -> {
            // Other unselected incorrect options retain neutral
            listOf(LightBackground, OutlineDivider, null, null)
        }
    }

    val letterBg = when {
        !isRevealed -> if (isSelected) PrimaryGreen else WhiteCards
        isCorrect -> PrimaryGreen
        isSelected -> LogoutRed
        else -> WhiteCards
    }

    val letterColor = when {
        !isRevealed -> if (isSelected) Color.White else PrimaryText
        isCorrect -> Color.White
        isSelected -> Color.White
        else -> PrimaryText
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor as Color)
            .border(BorderStroke(1.5.dp, borderColor as Color), RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                enabled = enabled,
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular option letter badge (A, B, C, D)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(letterBg, shape = CircleShape)
                    .border(BorderStroke(1.dp, OutlineDivider), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option.id,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = letterColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = option.text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected || (isRevealed && isCorrect)) FontWeight.SemiBold else FontWeight.Normal,
                color = PrimaryText,
                modifier = Modifier.weight(1f)
            )

            if (statusIcon != null && iconTint != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = statusIcon as androidx.compose.ui.graphics.vector.ImageVector,
                    contentDescription = null,
                    tint = iconTint as Color,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
