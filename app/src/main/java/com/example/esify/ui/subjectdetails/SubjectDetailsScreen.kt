package com.example.esify.ui.subjectdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.ChapterFilter
import com.example.esify.data.subject.SubjectCatalogue
import com.example.esify.ui.subjectdetails.components.ChapterCard
import com.example.esify.ui.subjectdetails.components.ChapterFilterChips
import com.example.esify.ui.subjectdetails.components.QuickResourcesCard
import com.example.esify.ui.subjectdetails.components.SubjectDetailsHeader
import com.example.esify.ui.subjectdetails.components.SubjectProgressCard
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText

@Composable
fun SubjectDetailsScreen(
    subjectId: String?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onChapterClick: (subjectId: String, chapterId: String) -> Unit = { _, _ -> }
) {
    val subject = remember(subjectId) {
        SubjectCatalogue.getSubject(subjectId)
    }

    var selectedFilter by remember(subject.id) {
        mutableStateOf(ChapterFilter.ALL)
    }

    val filterCounts = remember(subject) {
        mapOf(
            ChapterFilter.ALL to subject.chapters.size,
            ChapterFilter.NOT_STARTED to subject.chapters.count { it.progress == 0 },
            ChapterFilter.IN_PROGRESS to subject.chapters.count { it.progress in 1..99 },
            ChapterFilter.COMPLETED to subject.chapters.count { it.progress == 100 }
        )
    }

    val filteredChapters = remember(subject, selectedFilter) {
        when (selectedFilter) {
            ChapterFilter.ALL -> subject.chapters
            ChapterFilter.NOT_STARTED -> subject.chapters.filter { it.progress == 0 }
            ChapterFilter.IN_PROGRESS -> subject.chapters.filter { it.progress in 1..99 }
            ChapterFilter.COMPLETED -> subject.chapters.filter { it.progress == 100 }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Forest-green Header with Back Button
        SubjectDetailsHeader(
            subject = subject,
            onBackClick = onBackClick
        )

        // 2. Quick Resources Card (overlaps header)
        QuickResourcesCard(
            modifier = Modifier.offset(y = (-20).dp)
        )

        // 3. Subject Progress Card
        SubjectProgressCard(
            subject = subject,
            modifier = Modifier.offset(y = (-8).dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // 4. Chapter Status Filters
        ChapterFilterChips(
            selectedFilter = selectedFilter,
            onFilterSelected = { selectedFilter = it },
            filterCounts = filterCounts
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Section Title: Chapters list count
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chapters",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Text(
                text = "${filteredChapters.size} ${if (filteredChapters.size == 1) "Chapter" else "Chapters"}",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // 5. Chapter List
        if (filteredChapters.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No chapters found for this filter.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = SecondaryText
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                filteredChapters.forEach { chapter ->
                    ChapterCard(
                        chapter = chapter,
                        accentColor = subject.accentColor,
                        backgroundColor = subject.backgroundColor,
                        onClick = { onChapterClick(subject.id, chapter.id) }
                    )
                }
            }
        }

        // Bottom breathing room above the navigation bar
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectDetailsScreenPreview() {
    EsifyTheme {
        SubjectDetailsScreen(subjectId = "computer_science")
    }
}
