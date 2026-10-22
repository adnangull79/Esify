package com.example.esify.ui.subjects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esify.ui.subjects.components.ClassAndBoardCard
import com.example.esify.ui.subjects.components.KeepGoingBanner
import com.example.esify.ui.subjects.components.SelectSubjectGrid
import com.example.esify.ui.subjects.components.SubjectsHeader
import com.example.esify.ui.theme.EsifyTheme

@Composable
fun SubjectsScreen(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit = {},
    onClassAndBoardClick: () -> Unit = {},
    onSubjectClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // 1. Header with branding and subject selection greeting
        SubjectsHeader(
            onNotificationClick = onNotificationClick
        )

        // 2. Class and Board Card (visually connected with header)
        ClassAndBoardCard(
            modifier = Modifier.offset(y = (-20).dp),
            classNameText = "Class 9",
            boardText = "Board not selected",
            onClick = onClassAndBoardClick
        )

        // 3. Select a Subject Section
        SelectSubjectGrid(
            modifier = Modifier.offset(y = (-8).dp),
            onSubjectClick = { onSubjectClick(it.id) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 4. Keep Going Banner
        KeepGoingBanner(
            title = "Keep Going!",
            description = "Explore subjects and complete more chapters to improve your preparation.",
            onClick = { /* Non-functional placeholder as required */ }
        )

        // Bottom breathing room above the navigation bar
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectsScreenPreview() {
    EsifyTheme {
        SubjectsScreen()
    }
}
