package com.example.esify.ui.profile

import com.example.esify.ui.profile.model.DefaultAccountMenuItems
import com.example.esify.ui.profile.model.DefaultProfileStats
import com.example.esify.ui.profile.model.DefaultSettingsMenuItems
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProfileScreenTest {

    @Test
    fun profileStats_containsFourRequiredStats() {
        assertEquals(4, DefaultProfileStats.size)

        val chapters = DefaultProfileStats[0]
        assertEquals("12", chapters.value)
        assertTrue(chapters.label.contains("Chapters"))
        assertTrue(chapters.label.contains("Completed"))

        val mcqs = DefaultProfileStats[1]
        assertEquals("86", mcqs.value)
        assertTrue(mcqs.label.contains("MCQs"))
        assertTrue(mcqs.label.contains("Solved"))

        val quizzes = DefaultProfileStats[2]
        assertEquals("5", quizzes.value)
        assertTrue(quizzes.label.contains("Quizzes"))
        assertTrue(quizzes.label.contains("Taken"))

        val averageScore = DefaultProfileStats[3]
        assertEquals("68%", averageScore.value)
        assertTrue(averageScore.label.contains("Average"))
        assertTrue(averageScore.label.contains("Score"))
    }

    @Test
    fun boardPlaceholder_isNotHardcodedToFBISE() {
        val classAndBoardItem = DefaultAccountMenuItems.first { it.id == "class_board" }
        assertEquals("Class 9 • Board not selected", classAndBoardItem.subtitle)
        assertNotEquals("Federal Board (FBISE)", classAndBoardItem.subtitle)
        assertFalse(classAndBoardItem.subtitle.contains("FBISE"))
    }

    @Test
    fun accountMenuItems_matchRequiredOrderAndLabels() {
        assertEquals(4, DefaultAccountMenuItems.size)

        assertEquals("Class and Board", DefaultAccountMenuItems[0].title)
        assertEquals("Class 9 • Board not selected", DefaultAccountMenuItems[0].subtitle)

        assertEquals("My Progress", DefaultAccountMenuItems[1].title)
        assertEquals("View your detailed progress", DefaultAccountMenuItems[1].subtitle)

        assertEquals("Downloaded Content", DefaultAccountMenuItems[2].title)
        assertEquals("Manage your offline downloads", DefaultAccountMenuItems[2].subtitle)

        assertEquals("My Bookmarks", DefaultAccountMenuItems[3].title)
        assertEquals("View your saved topics and questions", DefaultAccountMenuItems[3].subtitle)
    }

    @Test
    fun settingsMenuItems_matchRequiredOrderAndLabels() {
        assertEquals(3, DefaultSettingsMenuItems.size)

        assertEquals("Settings", DefaultSettingsMenuItems[0].title)
        assertEquals("Theme, notifications and more", DefaultSettingsMenuItems[0].subtitle)

        assertEquals("Help & Support", DefaultSettingsMenuItems[1].title)
        assertEquals("FAQs, contact us", DefaultSettingsMenuItems[1].subtitle)

        assertEquals("About Esify", DefaultSettingsMenuItems[2].title)
        assertEquals("Version 1.0.0", DefaultSettingsMenuItems[2].subtitle)
    }
}
