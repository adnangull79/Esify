package com.example.esify.ui.chapterdetails

import com.example.esify.data.subject.SubjectCatalogue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ChapterDetailsScreenTest {

    @Test
    fun csChapter1_resolvesWithCorrectDetails() {
        val (subject, chapter) = SubjectCatalogue.getChapter("computer_science", "cs_ch1")

        assertEquals("Computer Science", subject.name)
        assertEquals(1, chapter.number)
        assertEquals("Introduction to Computer", chapter.title)
        assertTrue(chapter.detailedDescription.contains("Learn the basics of a computer"))

        assertEquals(8, chapter.totalTopicsCount)
        assertEquals(6, chapter.completedTopics)
        assertEquals(75, chapter.progressPercent)
    }

    @Test
    fun csChapter1Topics_matchReferenceData() {
        val (_, chapter) = SubjectCatalogue.getChapter("computer_science", "cs_ch1")
        val topics = chapter.topics

        assertEquals(8, topics.size)

        assertEquals(1, topics[0].number)
        assertEquals("What is a Computer?", topics[0].title)
        assertEquals(100, topics[0].progress)

        assertEquals(2, topics[1].number)
        assertEquals("Evolution (History) of Computer", topics[1].title)
        assertEquals(80, topics[1].progress)

        assertEquals(3, topics[2].number)
        assertEquals("Generations of Computer", topics[2].title)
        assertEquals(60, topics[2].progress)

        assertEquals(4, topics[3].number)
        assertEquals("Types of Computer", topics[3].title)
        assertEquals(40, topics[3].progress)

        assertEquals(5, topics[4].number)
        assertEquals("Components of a Computer System", topics[4].title)
        assertEquals(0, topics[4].progress)

        assertEquals(6, topics[5].number)
        assertEquals("Input and Output Devices", topics[5].title)
        assertEquals(0, topics[5].progress)

        assertEquals(7, topics[6].number)
        assertEquals("Applications of Computer", topics[6].title)
        assertEquals(0, topics[6].progress)

        assertEquals(8, topics[7].number)
        assertEquals("Advantages and Disadvantages", topics[7].title)
        assertEquals(0, topics[7].progress)
    }

    @Test
    fun differentChapters_resolveCorrectly() {
        val (_, ch2) = SubjectCatalogue.getChapter("computer_science", "cs_ch2")
        assertEquals(2, ch2.number)
        assertEquals("Data Representation", ch2.title)

        val (_, ch3) = SubjectCatalogue.getChapter("computer_science", "cs_ch3")
        assertEquals(3, ch3.number)
        assertEquals("Computer System", ch3.title)

        // Matching via digit "1" or "ch01"
        val (_, ch1ViaDigit) = SubjectCatalogue.getChapter("computer_science", "1")
        assertEquals(1, ch1ViaDigit.number)

        val (_, ch1ViaCh01) = SubjectCatalogue.getChapter("computer_science", "ch01")
        assertEquals(1, ch1ViaCh01.number)
    }

    @Test
    fun differentSubjects_resolveCorrectly() {
        val (phySub, phyCh1) = SubjectCatalogue.getChapter("physics", "phy_ch1")
        assertEquals("Physics", phySub.name)
        assertEquals("Physical Quantities & Measurement", phyCh1.title)

        val (chemSub, chemCh1) = SubjectCatalogue.getChapter("chemistry", "chem_ch1")
        assertEquals("Chemistry", chemSub.name)
        assertEquals("Fundamentals of Chemistry", chemCh1.title)

        val (bioSub, bioCh1) = SubjectCatalogue.getChapter("biology", "bio_ch1")
        assertEquals("Biology", bioSub.name)
        assertEquals("Introduction to Biology", bioCh1.title)
    }

    @Test
    fun emptyTopics_handlesSafelyWithoutDivisionByZero() {
        val (_, phyCh1) = SubjectCatalogue.getChapter("physics", "phy_ch1")
        assertEquals(0, phyCh1.totalTopicsCount)
        assertEquals(0, phyCh1.completedTopics)
        assertEquals(100, phyCh1.progressPercent)
    }

    @Test
    fun invalidOrNullIds_fallbacksGracefully() {
        val (nullSub, nullCh) = SubjectCatalogue.getChapter(null, null)
        assertNotNull(nullSub)
        assertNotNull(nullCh)
        assertEquals("Computer Science", nullSub.name)
        assertEquals(1, nullCh.number)

        val (invalidSub, invalidCh) = SubjectCatalogue.getChapter("unknown_subject", "unknown_ch")
        assertNotNull(invalidSub)
        assertNotNull(invalidCh)
        assertEquals("Computer Science", invalidSub.name)
        assertEquals(1, invalidCh.number)
    }
}
