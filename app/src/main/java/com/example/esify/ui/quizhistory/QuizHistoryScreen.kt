package com.example.esify.ui.quizhistory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.ui.theme.AuthCardBorder
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.AuthMintSurface
import com.example.esify.ui.theme.AuthSecondaryText
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightBackground
import com.example.esify.ui.theme.LightLeafGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.LightPurple
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.PurpleAccent
import com.example.esify.ui.theme.WhiteCards

data class QuizHistoryRecord(
    val id: String,
    val subjectId: String,
    val subjectName: String,
    val chapterTitle: String,
    val topicTitle: String,
    val dateText: String,
    val timeText: String,
    val totalMcqs: Int,
    val score: Int,
    val icon: ImageVector,
    val accentColor: Color,
    val backgroundColor: Color
) {
    val percentage: Int get() = if (totalMcqs > 0) (score * 100 / totalMcqs) else 0
    val scoreText: String get() = "$score / $totalMcqs"
}

@Composable
fun QuizHistoryScreen(
    onBackClick: () -> Unit = {},
    onViewQuizClick: (subjectId: String, chapterId: String, topicId: String) -> Unit = { _, _, _ -> },
    modifier: Modifier = Modifier
) {
    val allHistoryRecords = remember {
        listOf(
            QuizHistoryRecord(
                id = "history_1",
                subjectId = "computer_science",
                subjectName = "Computer Science",
                chapterTitle = "Chapter 1: Introduction to Computer",
                topicTitle = "Topic: History of Computer",
                dateText = "21 Oct 2026",
                timeText = "10:24 AM",
                totalMcqs = 10,
                score = 8,
                icon = Icons.Filled.Code,
                accentColor = PrimaryGreen,
                backgroundColor = LightMint
            ),
            QuizHistoryRecord(
                id = "history_2",
                subjectId = "physics",
                subjectName = "Physics",
                chapterTitle = "Chapter 2: Physical Quantities",
                topicTitle = "Topic: Units and Measurements",
                dateText = "20 Oct 2026",
                timeText = "04:15 PM",
                totalMcqs = 15,
                score = 10,
                icon = Icons.Filled.Bolt,
                accentColor = PurpleAccent,
                backgroundColor = LightPurple
            ),
            QuizHistoryRecord(
                id = "history_3",
                subjectId = "chemistry",
                subjectName = "Chemistry",
                chapterTitle = "Chapter 1: Fundamentals of Chemistry",
                topicTitle = "Topic: Matter and Its States",
                dateText = "19 Oct 2026",
                timeText = "11:32 AM",
                totalMcqs = 10,
                score = 9,
                icon = Icons.Filled.Science,
                accentColor = OrangeAccent,
                backgroundColor = LightOrange
            ),
            QuizHistoryRecord(
                id = "history_4",
                subjectId = "biology",
                subjectName = "Biology",
                chapterTitle = "Chapter 3: Cell Structure",
                topicTitle = "Topic: Plant and Animal Cell",
                dateText = "18 Oct 2026",
                timeText = "02:48 PM",
                totalMcqs = 20,
                score = 15,
                icon = Icons.Filled.Spa,
                accentColor = LeafGreen,
                backgroundColor = LightLeafGreen
            ),
            QuizHistoryRecord(
                id = "history_5",
                subjectId = "computer_science",
                subjectName = "Computer Science",
                chapterTitle = "Chapter 2: Data Representation",
                topicTitle = "Topic: Number Systems",
                dateText = "16 Oct 2026",
                timeText = "09:10 AM",
                totalMcqs = 10,
                score = 6,
                icon = Icons.Filled.Code,
                accentColor = PrimaryGreen,
                backgroundColor = LightMint
            ),
            QuizHistoryRecord(
                id = "history_6",
                subjectId = "physics",
                subjectName = "Physics",
                chapterTitle = "Chapter 1: Introduction to Physics",
                topicTitle = "Topic: Nature of Physics",
                dateText = "14 Oct 2026",
                timeText = "01:22 PM",
                totalMcqs = 10,
                score = 10,
                icon = Icons.Filled.Bolt,
                accentColor = PurpleAccent,
                backgroundColor = LightPurple
            ),
            QuizHistoryRecord(
                id = "history_7",
                subjectId = "chemistry",
                subjectName = "Chemistry",
                chapterTitle = "Chapter 2: Elements, Compounds and Mixtures",
                topicTitle = "Topic: Pure Substances and Mixtures",
                dateText = "12 Oct 2026",
                timeText = "03:40 PM",
                totalMcqs = 15,
                score = 8,
                icon = Icons.Filled.Science,
                accentColor = OrangeAccent,
                backgroundColor = LightOrange
            )
        )
    }

    var selectedFilter by remember { mutableStateOf("All") }
    val filterCategories = remember { listOf("All", "Computer Science", "Physics", "Chemistry", "Biology") }

    val filteredRecords = remember(selectedFilter) {
        if (selectedFilter == "All") {
            allHistoryRecords
        } else {
            allHistoryRecords.filter { it.subjectName.equals(selectedFilter, ignoreCase = true) }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            // Branded Top Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(AuthDarkGreen)
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Quiz History",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "View all your completed quizzes and track your progress.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                ),
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White.copy(alpha = 0.15f), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.School,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filter Chips Row with Calendar Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    filterCategories.forEach { category ->
                        val isSelected = category == selectedFilter
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) AuthDarkGreen else AuthMintSurface,
                            border = BorderStroke(1.dp, if (isSelected) AuthDarkGreen else Color(0xFFD4E8DC)),
                            modifier = Modifier.clickable { selectedFilter = category }
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                ),
                                color = if (isSelected) Color.White else AuthDarkGreen,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Calendar Action Button
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = AuthMintSurface,
                    border = BorderStroke(1.dp, Color(0xFFD4E8DC)),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = "Filter by date",
                            tint = AuthDarkGreen,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quiz History Items List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                filteredRecords.forEach { record ->
                    QuizHistoryCard(
                        record = record,
                        onViewClick = {
                            onViewQuizClick(record.subjectId, "ch1", "t1")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun QuizHistoryCard(
    record: QuizHistoryRecord,
    onViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards),
        border = BorderStroke(1.dp, AuthCardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Subject Icon Badge
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(record.backgroundColor, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = record.icon,
                        contentDescription = null,
                        tint = record.accentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Subject Title & Chapter/Topic Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = record.subjectName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = PrimaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = record.chapterTitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.5.sp
                        ),
                        color = AuthSecondaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = record.topicTitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 11.sp
                        ),
                        color = AuthSecondaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Percentage Ring & Score Column
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Circular percentage indicator
                        Box(
                            modifier = Modifier.size(38.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                progress = { record.percentage / 100f },
                                modifier = Modifier.fillMaxSize(),
                                color = PrimaryGreen,
                                trackColor = AuthMintSurface,
                                strokeWidth = 3.dp
                            )
                            Text(
                                text = "${record.percentage}%",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = PrimaryText
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = record.scoreText,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = PrimaryText
                            )
                            Text(
                                text = "Score",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = AuthSecondaryText
                            )
                        }

                        Spacer(modifier = Modifier.width(2.dp))

                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Options",
                            tint = AuthSecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Footer Row: Date, Time, MCQ Count Chip, and View Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = null,
                            tint = AuthSecondaryText,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = record.dateText,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.5.sp),
                            color = AuthSecondaryText
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.AccessTime,
                            contentDescription = null,
                            tint = AuthSecondaryText,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = record.timeText,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.5.sp),
                            color = AuthSecondaryText
                        )
                    }

                    // MCQ Count Chip
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = record.backgroundColor,
                        border = BorderStroke(1.dp, record.accentColor.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = "${record.totalMcqs} MCQs",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = record.accentColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                // View -> Action Button
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = AuthMintSurface,
                    border = BorderStroke(1.dp, Color(0xFFD4E8DC)),
                    modifier = Modifier.clickable { onViewClick() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "View",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = AuthDarkGreen
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = AuthDarkGreen,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizHistoryScreenPreview() {
    EsifyTheme {
        QuizHistoryScreen()
    }
}
