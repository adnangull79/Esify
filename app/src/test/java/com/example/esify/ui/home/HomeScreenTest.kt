package com.example.esify.ui.home

import com.example.esify.ui.home.components.DefaultSubjects
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeScreenTest {

    @Test
    fun defaultSubjects_containExpectedFourSubjects() {
        assertEquals(4, DefaultSubjects.size)

        val names = DefaultSubjects.map { it.name }
        assertTrue(names.contains("Computer Science"))
        assertTrue(names.contains("Physics"))
        assertTrue(names.contains("Chemistry"))
        assertTrue(names.contains("Biology"))
    }

    @Test
    fun defaultSubjects_progressValuesAreValid() {
        DefaultSubjects.forEach { subject ->
            assertTrue("Progress must be between 0 and 1", subject.progress in 0f..1f)
            assertTrue("Completed chapters must not exceed total", subject.completedChapters <= subject.totalChapters)
            assertTrue("Total chapters must be positive", subject.totalChapters > 0)
        }
    }
}
