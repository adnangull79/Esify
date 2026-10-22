package com.example.esify.ui.topicpractice.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.SecondaryText

@Composable
fun QuestionNavigationButtons(
    currentIndex: Int,
    totalCount: Int,
    isQuizMode: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    onSubmitQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (totalCount <= 0) return

    val canGoPrevious = currentIndex > 0
    val isLastQuestion = currentIndex >= totalCount - 1

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous Button
        OutlinedButton(
            onClick = onPreviousClick,
            enabled = canGoPrevious,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, if (canGoPrevious) PrimaryGreen else OutlineDivider),
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = PrimaryGreen,
                disabledContentColor = SecondaryText.copy(alpha = 0.5f)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Previous",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Next or Submit Button
        if (isQuizMode && isLastQuestion) {
            Button(
                onClick = onSubmitQuiz,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
            ) {
                Text(
                    text = "Submit Quiz",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Submit",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        } else {
            Button(
                onClick = onNextClick,
                enabled = !isLastQuestion,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    disabledContainerColor = OutlineDivider
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (!isLastQuestion) Color.White else SecondaryText
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next",
                    tint = if (!isLastQuestion) Color.White else SecondaryText,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
