package com.example.esify.ui.subjectdetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.ChapterFilter
import com.example.esify.ui.theme.DarkForestGreen
import com.example.esify.ui.theme.OutlineDivider
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ChapterFilterChips(
    selectedFilter: ChapterFilter,
    onFilterSelected: (ChapterFilter) -> Unit,
    filterCounts: Map<ChapterFilter, Int>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChapterFilter.entries.forEach { filter ->
            val isSelected = filter == selectedFilter
            val count = filterCounts[filter] ?: 0
            val chipLabel = "${filter.label} ($count)"

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = if (isSelected) DarkForestGreen else WhiteCards,
                border = if (isSelected) null else BorderStroke(1.dp, OutlineDivider),
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = { onFilterSelected(filter) }
                )
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chipLabel,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) WhiteCards else SecondaryText
                    )
                }
            }
        }
    }
}
