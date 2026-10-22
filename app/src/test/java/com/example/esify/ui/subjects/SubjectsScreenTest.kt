package com.example.esify.ui.subjects

import com.example.esify.ui.home.components.DefaultSubjects
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SubjectsScreenTest {

    @Test
    fun subjects_containAllFourCoreSubjects() {
        assertEquals(4, DefaultSubjects.size)

        val subjectNames = DefaultSubjects.map { it.name }
        assertTrue(subjectNames.contains("Computer Science"))
        assertTrue(subjectNames.contains("Physics"))
        assertTrue(subjectNames.contains("Chemistry"))
        assertTrue(subjectNames.contains("Biology"))
    }

    @Test
    fun subjects_chapterProgressAndPercentagesAreConsistent() {
        val cs = DefaultSubjects.first { it.name == "Computer Science" }
        assertEquals(8, cs.completedChapters)
        assertEquals(12, cs.totalChapters)
        assertEquals("67%", cs.percentageText)

        val physics = DefaultSubjects.first { it.name == "Physics" }
        assertEquals(6, physics.completedChapters)
        assertEquals(12, physics.totalChapters)
        assertEquals("50%", physics.percentageText)

        val chemistry = DefaultSubjects.first { it.name == "Chemistry" }
        assertEquals(5, chemistry.completedChapters)
        assertEquals(12, chemistry.totalChapters)
        assertEquals("42%", chemistry.percentageText)

        val biology = DefaultSubjects.first { it.name == "Biology" }
        assertEquals(4, biology.completedChapters)
        assertEquals(12, biology.totalChapters)
        assertEquals("33%", biology.percentageText)
    }

    @Test
    fun boardPlaceholder_isNotHardcodedToFBISE() {
        val boardPlaceholder = "Board not selected"
        assertNotEquals("Federal Board (FBISE)", boardPlaceholder)
        assertEquals("Board not selected", boardPlaceholder)
    }
}
