package com.example.esify.ui.chapterdetails

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.data.subject.SubjectCatalogue
import com.example.esify.data.subject.TopicItem
import com.example.esify.ui.chapterdetails.components.ChapterActionTiles
import com.example.esify.ui.chapterdetails.components.ChapterDetailsHeader
import com.example.esify.ui.chapterdetails.components.ChapterProgressCard
import com.example.esify.ui.chapterdetails.components.TopicRow
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun ChapterDetailsScreen(
    subjectId: String?,
    chapterId: String?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onTopicClick: (TopicItem) -> Unit = {},
    onTopicMcqsClick: (subjectId: String, chapterId: String, topicId: String) -> Unit = { _, _, _ -> },
    onTopicQuestionsClick: (subjectId: String, chapterId: String, topicId: String) -> Unit = { _, _, _ -> },
    onTopicQuizClick: (subjectId: String, chapterId: String, topicId: String) -> Unit = { _, _, _ -> }
) {
    val (subject, chapter) = remember(subjectId, chapterId) {
        SubjectCatalogue.getChapter(subjectId, chapterId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Forest-green Chapter Header with Back Navigation
        ChapterDetailsHeader(
            subject = subject,
            chapter = chapter,
            onBackClick = onBackClick
        )

        // 2. Chapter Progress Card (overlaps header)
        ChapterProgressCard(
            chapter = chapter,
            accentColor = subject.accentColor,
            modifier = Modifier.offset(y = (-20).dp)
        )

        // 3. Chapter Action Tiles (MCQs, Short Questions, Quiz, Download)
        val defaultTopicId = when (subject.id.lowercase().trim()) {
            "physics" -> "phy_ch1_t1"
            "chemistry" -> "chem_ch1_t1"
            "biology" -> "bio_ch1_t1"
            else -> "cs_ch1_t1"
        }
        ChapterActionTiles(
            modifier = Modifier.offset(y = (-8).dp),
            onMcqsClick = {
                val topicId = chapter.topics.firstOrNull()?.id ?: defaultTopicId
                onTopicMcqsClick(subject.id, chapter.id, topicId)
            },
            onShortQuestionsClick = {
                val topicId = chapter.topics.firstOrNull()?.id ?: defaultTopicId
                onTopicQuestionsClick(subject.id, chapter.id, topicId)
            },
            onQuizClick = {
                val topicId = chapter.topics.firstOrNull()?.id ?: defaultTopicId
                onTopicQuizClick(subject.id, chapter.id, topicId)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 4. Topics Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Topics in this Chapter",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Text(
                text = "${chapter.totalTopicsCount} ${if (chapter.totalTopicsCount == 1) "Topic" else "Topics"}",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // 5. Topics List or Empty State
        if (chapter.topics.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = WhiteCards)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No topics available for this chapter yet.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryText
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                chapter.topics.forEach { topic ->
                    TopicRow(
                        topic = topic,
                        onClick = {
                            onTopicClick(topic)
                            onTopicMcqsClick(subject.id, chapter.id, topic.id)
                        },
                        onMcqsClick = {
                            onTopicMcqsClick(subject.id, chapter.id, topic.id)
                        },
                        onQuestionsClick = {
                            onTopicQuestionsClick(subject.id, chapter.id, topic.id)
                        }
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
fun ChapterDetailsScreenPreview() {
    EsifyTheme {
        ChapterDetailsScreen(
            subjectId = "computer_science",
            chapterId = "cs_ch1"
        )
    }
}
