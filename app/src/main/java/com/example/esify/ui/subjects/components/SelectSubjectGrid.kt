package com.example.esify.ui.subjects.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.esify.ui.home.components.DefaultSubjects
import com.example.esify.ui.home.components.SubjectCard
import com.example.esify.ui.home.model.SubjectProgressItem
import com.example.esify.ui.theme.PrimaryText

@Composable
fun SelectSubjectGrid(
    modifier: Modifier = Modifier,
    subjects: List<SubjectProgressItem> = DefaultSubjects,
    onSubjectClick: (SubjectProgressItem) -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Select a Subject",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            subjects.chunked(2).forEach { rowSubjects ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowSubjects.forEach { subject ->
                        SubjectCard(
                            subject = subject,
                            modifier = Modifier.weight(1f),
                            onClick = { onSubjectClick(subject) }
                        )
                    }
                    if (rowSubjects.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
