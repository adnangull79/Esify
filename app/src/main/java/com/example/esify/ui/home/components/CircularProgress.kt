package com.example.esify.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.ProgressTrack

@Composable
fun CircularProgress(
    progress: Float,
    percentageText: String,
    modifier: Modifier = Modifier,
    size: Dp = 68.dp,
    strokeWidth: Dp = 7.dp,
    color: Color = PrimaryGreen,
    trackColor: Color = ProgressTrack
) {
    val clampedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val strokePx = strokeWidth.toPx()
            val arcSize = size.toPx() - strokePx

            // Background track
            drawCircle(
                color = trackColor,
                radius = arcSize / 2,
                style = Stroke(width = strokePx)
            )

            // Active progress arc
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = clampedProgress * 360f,
                useCenter = false,
                style = Stroke(width = strokePx, cap = StrokeCap.Round)
            )
        }

        Text(
            text = percentageText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
