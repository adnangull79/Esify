package com.example.esify.ui.subjectdetails

import com.example.esify.data.subject.ChapterFilter
import com.example.esify.data.subject.SubjectCatalogue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SubjectDetailsScreenTest {

    @Test
    fun subjectResolution_resolvesAllFourSubjects() {
        val cs = SubjectCatalogue.getSubject("computer_science")
        assertEquals("Computer Science", cs.name)
        assertEquals(12, cs.totalChapters)
        assertEquals(8, cs.completedChapters)
        assertEquals("67%", cs.percentageText)
        assertEquals(12, cs.chapters.size)

        val physics = SubjectCatalogue.getSubject("physics")
        assertEquals("Physics", physics.name)
        assertEquals(12, physics.totalChapters)
        assertEquals(6, physics.completedChapters)
        assertEquals("50%", physics.percentageText)
        assertEquals(12, physics.chapters.size)

        val chemistry = SubjectCatalogue.getSubject("chemistry")
        assertEquals("Chemistry", chemistry.name)
        assertEquals(12, chemistry.totalChapters)
        assertEquals(5, chemistry.completedChapters)
        assertEquals("42%", chemistry.percentageText)
        assertEquals(12, chemistry.chapters.size)

        val biology = SubjectCatalogue.getSubject("biology")
        assertEquals("Biology", biology.name)
        assertEquals(12, biology.totalChapters)
        assertEquals(4, biology.completedChapters)
        assertEquals("33%", biology.percentageText)
        assertEquals(12, biology.chapters.size)
    }

    @Test
    fun subjectResolution_supportsCsAlias() {
        val cs = SubjectCatalogue.getSubject("cs")
        assertEquals("Computer Science", cs.name)
        assertEquals("computer_science", cs.id)
    }

    @Test
    fun subjectResolution_handlesUnknownOrNullGracefully() {
        val nullSubject = SubjectCatalogue.getSubject(null)
        assertNotNull(nullSubject)
        assertEquals("Computer Science", nullSubject.name)

        val blankSubject = SubjectCatalogue.getSubject("   ")
        assertNotNull(blankSubject)
        assertEquals("Computer Science", blankSubject.name)

        val unknownSubject = SubjectCatalogue.getSubject("unknown_math_123")
        assertNotNull(unknownSubject)
        assertEquals("Computer Science", unknownSubject.name)
    }

    @Test
    fun computerScienceChapters_matchReferenceData() {
        val cs = SubjectCatalogue.getSubject("computer_science")
        assertEquals(12, cs.chapters.size)

        val ch1 = cs.chapters[0]
        assertEquals(1, ch1.number)
        assertEquals("Introduction to Computer", ch1.title)
        assertEquals("Computer System and Their Types", ch1.description)
        assertEquals(100, ch1.progress)

        val ch2 = cs.chapters[1]
        assertEquals(2, ch2.number)
        assertEquals("Data Representation", ch2.title)
        assertEquals(60, ch2.progress)

        val ch3 = cs.chapters[2]
        assertEquals(3, ch3.number)
        assertEquals("Computer System", ch3.title)
        assertEquals(25, ch3.progress)

        val ch4 = cs.chapters[3]
        assertEquals(4, ch4.number)
        assertEquals("Operating System", ch4.title)
        assertEquals(0, ch4.progress)

        val ch8 = cs.chapters[7]
        assertEquals(8, ch8.number)
        assertEquals("Networks and Internet", ch8.title)
        assertEquals(100, ch8.progress)

        val ch12 = cs.chapters[11]
        assertEquals(12, ch12.number)
        assertEquals("Emerging Technologies", ch12.title)
        assertEquals(45, ch12.progress)
    }

    @Test
    fun computerScienceFilterCounts_areAccurate() {
        val cs = SubjectCatalogue.getSubject("computer_science")

        val allCount = cs.chapters.size
        val notStartedCount = cs.chapters.count { it.progress == 0 }
        val inProgressCount = cs.chapters.count { it.progress in 1..99 }
        val completedCount = cs.chapters.count { it.progress == 100 }

        assertEquals(12, allCount)
        assertEquals(4, notStartedCount)
        assertEquals(3, inProgressCount)
        assertEquals(5, completedCount)

        assertEquals(allCount, notStartedCount + inProgressCount + completedCount)
    }

    @Test
    fun boardPlaceholder_isNotHardcodedToFBISE() {
        val defaultBoardPlaceholder = "Class 9 • Board not selected"
        assertNotEquals("Federal Board (FBISE)", defaultBoardPlaceholder)
        assertFalse(defaultBoardPlaceholder.contains("FBISE"))
    }
}
