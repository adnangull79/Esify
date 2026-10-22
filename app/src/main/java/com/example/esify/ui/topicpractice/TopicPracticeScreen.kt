package com.example.esify.ui.topicpractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.data.question.PracticeMode
import com.example.esify.data.question.QuestionCatalogue
import com.example.esify.ui.theme.EsifyTheme
import com.example.esify.ui.theme.SecondaryText
import com.example.esify.ui.theme.WhiteCards
import com.example.esify.ui.topicpractice.components.McqCard
import com.example.esify.ui.topicpractice.components.PracticeModeTabs
import com.example.esify.ui.topicpractice.components.QuestionNavigationButtons
import com.example.esify.ui.topicpractice.components.QuestionProgressRow
import com.example.esify.ui.topicpractice.components.QuizSummaryCard
import com.example.esify.ui.topicpractice.components.ShortQuestionCard
import com.example.esify.ui.topicpractice.components.TopicPracticeTopBar

@Composable
fun TopicPracticeScreen(
    subjectId: String?,
    chapterId: String?,
    topicId: String?,
    modifier: Modifier = Modifier,
    initialModeString: String? = "mcq",
    onBackClick: () -> Unit = {}
) {
    val (subject, chapter, topic) = remember(subjectId, chapterId, topicId) {
        QuestionCatalogue.resolveTopic(subjectId, chapterId, topicId)
    }

    val mcqs = remember(topic.id) {
        QuestionCatalogue.getMcqs(topic.id)
    }

    val shortQuestions = remember(topic.id, subject.id) {
        QuestionCatalogue.getShortQuestions(topic.id, subject.id)
    }

    var currentMode by remember(initialModeString) {
        mutableStateOf(PracticeMode.fromRoute(initialModeString))
    }

    var mcqIndex by remember(topic.id, currentMode) {
        mutableIntStateOf(0)
    }

    var selectedOptionId by remember(topic.id, mcqIndex, currentMode) {
        mutableStateOf<String?>(null)
    }

    var isAnswerRevealed by remember(topic.id, mcqIndex, currentMode) {
        mutableStateOf(false)
    }

    var isTopicBookmarked by remember(topic.id) {
        mutableStateOf(false)
    }

    // Scoped individual question bookmarks for short questions
    val bookmarkedShortQuestionIds = remember(topic.id) {
        mutableStateMapOf<String, Boolean>()
    }

    // Quiz state tracking
    val quizAnswers = remember(topic.id) {
        mutableStateMapOf<Int, String>()
    }
    var isQuizSubmitted by remember(topic.id) {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. Top Bar with back arrow, subject/chapter/topic titles, and bookmark
        TopicPracticeTopBar(
            subject = subject,
            chapter = chapter,
            topic = topic,
            isBookmarked = isTopicBookmarked,
            onBookmarkToggle = { isTopicBookmarked = !isTopicBookmarked },
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 2. Selectable Mode Tabs (MCQs, Short Questions, Take Quiz)
        PracticeModeTabs(
            selectedMode = currentMode,
            onModeSelected = { newMode ->
                if (currentMode != newMode) {
                    currentMode = newMode
                    isAnswerRevealed = false
                    selectedOptionId = null
                }
            }
        )

        Spacer(modifier = Modifier.height(6.dp))

        when (currentMode) {
            PracticeMode.MCQ -> {
                if (mcqs.isEmpty()) {
                    EmptyQuestionState(message = "No MCQs available for this topic yet.")
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val safeIndex = mcqIndex.coerceIn(0, mcqs.lastIndex)
                        val currentQuestion = mcqs[safeIndex]

                        QuestionProgressRow(
                            currentIndex = safeIndex,
                            totalCount = mcqs.size
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        McqCard(
                            question = currentQuestion,
                            selectedOptionId = selectedOptionId,
                            isRevealed = isAnswerRevealed,
                            isQuizMode = false,
                            onOptionSelect = { optionId ->
                                if (!isAnswerRevealed) {
                                    selectedOptionId = optionId
                                }
                            },
                            onRevealAnswer = {
                                isAnswerRevealed = true
                            },
                            isBookmarked = isTopicBookmarked,
                            onBookmarkToggle = { isTopicBookmarked = !isTopicBookmarked }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        QuestionNavigationButtons(
                            currentIndex = safeIndex,
                            totalCount = mcqs.size,
                            isQuizMode = false,
                            onPreviousClick = {
                                if (safeIndex > 0) {
                                    mcqIndex = safeIndex - 1
                                    isAnswerRevealed = false
                                    selectedOptionId = null
                                }
                            },
                            onNextClick = {
                                if (safeIndex < mcqs.lastIndex) {
                                    mcqIndex = safeIndex + 1
                                    isAnswerRevealed = false
                                    selectedOptionId = null
                                }
                            },
                            onSubmitQuiz = {}
                        )

                        Spacer(modifier = Modifier.height(28.dp))
                    }
                }
            }

            PracticeMode.SHORT_QUESTIONS -> {
                if (shortQuestions.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        QuestionProgressRow(
                            currentIndex = 0,
                            totalCount = 0
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        EmptyQuestionState(message = "No short questions available for this topic yet.")
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                } else {
                    // Documented Rule: Progress tracks the question currently viewed at the top of the viewport
                    val shortQListState = rememberLazyListState()
                    val currentViewedQuestion = remember(shortQuestions.size) {
                        derivedStateOf {
                            (shortQListState.firstVisibleItemIndex + 1).coerceIn(1, shortQuestions.size)
                        }
                    }

                    QuestionProgressRow(
                        currentIndex = currentViewedQuestion.value - 1,
                        totalCount = shortQuestions.size
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        state = shortQListState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        itemsIndexed(
                            items = shortQuestions,
                            key = { _, sq -> sq.id }
                        ) { _, shortQuestion ->
                            val isBookmarked = bookmarkedShortQuestionIds[shortQuestion.id] == true
                            ShortQuestionCard(
                                question = shortQuestion,
                                topicTitle = topic.title,
                                isBookmarked = isBookmarked,
                                onBookmarkToggle = {
                                    bookmarkedShortQuestionIds[shortQuestion.id] = !isBookmarked
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(28.dp))
                        }
                    }
                }
            }

            PracticeMode.QUIZ -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    if (mcqs.isEmpty()) {
                        EmptyQuestionState(message = "No quiz questions available for this topic yet.")
                    } else if (isQuizSubmitted) {
                        val correctCount = mcqs.indices.count { idx ->
                            quizAnswers[idx] == mcqs[idx].correctOptionId
                        }
                        QuizSummaryCard(
                            correctCount = correctCount,
                            totalCount = mcqs.size,
                            onRestartQuiz = {
                                quizAnswers.clear()
                                mcqIndex = 0
                                isQuizSubmitted = false
                            },
                            onReviewQuestions = {
                                currentMode = PracticeMode.MCQ
                                mcqIndex = 0
                                isAnswerRevealed = true
                            }
                        )
                    } else {
                        val safeIndex = mcqIndex.coerceIn(0, mcqs.lastIndex)
                        val currentQuestion = mcqs[safeIndex]
                        val chosenAnswer = quizAnswers[safeIndex]

                        QuestionProgressRow(
                            currentIndex = safeIndex,
                            totalCount = mcqs.size
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        McqCard(
                            question = currentQuestion,
                            selectedOptionId = chosenAnswer,
                            isRevealed = false,
                            isQuizMode = true,
                            onOptionSelect = { optionId ->
                                quizAnswers[safeIndex] = optionId
                            },
                            onRevealAnswer = {},
                            isBookmarked = isTopicBookmarked,
                            onBookmarkToggle = { isTopicBookmarked = !isTopicBookmarked }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        QuestionNavigationButtons(
                            currentIndex = safeIndex,
                            totalCount = mcqs.size,
                            isQuizMode = true,
                            onPreviousClick = {
                                if (safeIndex > 0) {
                                    mcqIndex = safeIndex - 1
                                }
                            },
                            onNextClick = {
                                if (safeIndex < mcqs.lastIndex) {
                                    mcqIndex = safeIndex + 1
                                }
                            },
                            onSubmitQuiz = {
                                isQuizSubmitted = true
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                }
            }
        }
    }
}

@Composable
private fun EmptyQuestionState(
    message: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteCards)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPracticeScreenPreview() {
    EsifyTheme {
        TopicPracticeScreen(
            subjectId = "computer_science",
            chapterId = "cs_ch1",
            topicId = "cs_ch1_t1"
        )
    }
}
