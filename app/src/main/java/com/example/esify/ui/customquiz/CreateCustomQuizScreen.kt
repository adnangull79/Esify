package com.example.esify.ui.customquiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esify.data.subject.ChapterItem
import com.example.esify.data.subject.SubjectCatalogue
import com.example.esify.data.subject.SubjectDetailsItem
import com.example.esify.data.subject.TopicItem
import com.example.esify.ui.theme.AuthCardBorder
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.AuthMintSurface
import com.example.esify.ui.theme.AuthSecondaryText
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.LightBackground
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PrimaryText
import com.example.esify.ui.theme.WhiteCards

@Composable
fun CreateCustomQuizScreen(
    onBackClick: () -> Unit = {},
    onCreateQuizClick: (subjectId: String, chapterId: String, topicId: String, mcqCount: Int) -> Unit = { _, _, _, _ -> },
    modifier: Modifier = Modifier
) {
    val allSubjects = remember { SubjectCatalogue.getAllSubjects() }

    var selectedSubject by remember { mutableStateOf(allSubjects.first()) }
    var selectedChapter by remember { mutableStateOf(selectedSubject.chapters.first()) }

    val currentTopics = remember(selectedChapter) {
        val topicsList = selectedChapter.topics
        if (topicsList.isNotEmpty()) {
            listOf(TopicItem("all_topics", 0, "All Topics (${selectedChapter.title})", 100)) + topicsList
        } else {
            listOf(TopicItem("all_topics", 0, "All Topics in Chapter", 100))
        }
    }

    var selectedTopic by remember(selectedChapter) { mutableStateOf(currentTopics.first()) }
    var mcqCount by remember { mutableFloatStateOf(10f) }

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
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Top Section - Back Button & Header Title
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
                        tint = AuthDarkGreen
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Create Custom Quiz",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = AuthDarkGreen
                    )
                    Text(
                        text = "Build a tailored test for your exam preparation",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        ),
                        color = AuthSecondaryText
                    )
                }
            }

            // Hero Header Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = AuthMintSurface),
                border = BorderStroke(1.dp, Color(0xFFD4E8DC))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Psychology,
                            contentDescription = null,
                            tint = AuthDarkGreen,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Custom Practice Test",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = AuthDarkGreen
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Select subject, chapter, topic and MCQ count to generate your custom quiz instantly.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            ),
                            color = AuthSecondaryText
                        )
                    }
                }
            }

            // Selection Form Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = WhiteCards),
                border = BorderStroke(1.dp, AuthCardBorder),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 1. Subject Selector
                    CustomDropdownSelector(
                        label = "Select Subject",
                        selectedText = selectedSubject.name,
                        leadingIcon = Icons.Filled.Category,
                        options = allSubjects,
                        optionToText = { it.name },
                        onOptionSelected = { subject ->
                            selectedSubject = subject
                            selectedChapter = subject.chapters.first()
                        }
                    )

                    // 2. Chapter Selector
                    CustomDropdownSelector(
                        label = "Select Chapter",
                        selectedText = "Ch ${selectedChapter.number}: ${selectedChapter.title}",
                        leadingIcon = Icons.Filled.Book,
                        options = selectedSubject.chapters,
                        optionToText = { "Ch ${it.number}: ${it.title}" },
                        onOptionSelected = { chapter ->
                            selectedChapter = chapter
                        }
                    )

                    // 3. Topic Selector
                    CustomDropdownSelector(
                        label = "Select Topic",
                        selectedText = selectedTopic.title,
                        leadingIcon = Icons.Filled.Bookmark,
                        options = currentTopics,
                        optionToText = { it.title },
                        onOptionSelected = { topic ->
                            selectedTopic = topic
                        }
                    )

                    // 4. Number of MCQs Slider
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(AuthMintSurface, shape = RoundedCornerShape(10.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.FormatListNumbered,
                                        contentDescription = null,
                                        tint = AuthDarkGreen,
                                        modifier = Modifier.size(19.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Number of MCQs",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = PrimaryText
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = AuthMintSurface,
                                border = BorderStroke(1.dp, Color(0xFFD4E8DC))
                            ) {
                                Text(
                                    text = "${mcqCount.toInt()} Questions",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = AuthDarkGreen,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Slider(
                            value = mcqCount,
                            onValueChange = { mcqCount = it },
                            valueRange = 5f..30f,
                            steps = 4, // 5, 10, 15, 20, 25, 30
                            colors = SliderDefaults.colors(
                                thumbColor = AuthDarkGreen,
                                activeTrackColor = PrimaryGreen,
                                inactiveTrackColor = Color(0xFFE4EDE7)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            listOf(5, 10, 15, 20, 25, 30).forEach { count ->
                                Text(
                                    text = "$count",
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                    color = if (mcqCount.toInt() == count) AuthDarkGreen else AuthSecondaryText,
                                    fontWeight = if (mcqCount.toInt() == count) FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier.clickable { mcqCount = count.toFloat() }
                                )
                            }
                        }
                    }
                }
            }

            // Create Quiz Primary Action Button
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clickable {
                        val topicIdToUse = if (selectedTopic.id == "all_topics") {
                            selectedChapter.topics.firstOrNull()?.id ?: "cs_ch1_t1"
                        } else {
                            selectedTopic.id
                        }
                        onCreateQuizClick(
                            selectedSubject.id,
                            selectedChapter.id,
                            topicIdToUse,
                            mcqCount.toInt()
                        )
                    },
                shape = RoundedCornerShape(26.dp),
                color = AuthDarkGreen,
                shadowElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Create Quiz",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun <T> CustomDropdownSelector(
    label: String,
    selectedText: String,
    leadingIcon: ImageVector,
    options: List<T>,
    optionToText: (T) -> String,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            color = PrimaryText,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFAFCFA),
                border = BorderStroke(1.dp, Color(0xFFE4EDE7))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(AuthMintSurface, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = AuthDarkGreen,
                            modifier = Modifier.size(19.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = selectedText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = PrimaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown",
                        tint = AuthSecondaryText,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(WhiteCards)
            ) {
                options.forEach { option ->
                    val text = optionToText(option)
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 13.5.sp,
                                    fontWeight = if (text == selectedText) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (text == selectedText) AuthDarkGreen else PrimaryText
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateCustomQuizScreenPreview() {
    EsifyTheme {
        CreateCustomQuizScreen()
    }
}
