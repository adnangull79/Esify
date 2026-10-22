package com.example.esify.ui.onboarding

import com.example.esify.data.preferences.OnboardingPreferences
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class OnboardingScreenTest {

    @Test
    fun pages_hasExactCountOfThree() {
        assertEquals("Onboarding must have exactly 3 pages", 3, OnboardingPageData.pages.size)
    }

    @Test
    fun pages_sequentialPageNumbers() {
        OnboardingPageData.pages.forEachIndexed { index, page ->
            assertEquals("Page index + 1 must match pageNumber", index + 1, page.pageNumber)
        }
    }

    @Test
    fun page1_contentMatchesSpecification() {
        val page1 = OnboardingPageData.pages[0]
        assertEquals("Master Class 9 & 10 Boards", page1.heading)
        assertEquals(
            "Your all-in-one companion for Matric exam preparation with curriculum-aligned study materials.",
            page1.description
        )
        assertEquals(4, page1.features.size)

        val titles = page1.features.map { it.title }
        assertEquals("Sindh & Federal Boards", titles[0])
        assertEquals("All 4 Core Subjects", titles[1])
        assertEquals("Offline Access", titles[2])
        assertEquals("100% Free Prep", titles[3])

        assertEquals("Targeted board syllabus", page1.features[0].subtitle)
        assertEquals("CS, Physics, Chem & Bio", page1.features[1].subtitle)
        assertEquals("Download & study anywhere", page1.features[2].subtitle)
        assertEquals("No subscriptions required", page1.features[3].subtitle)
    }

    @Test
    fun page2_contentMatchesSpecification() {
        val page2 = OnboardingPageData.pages[1]
        assertEquals("Learn Better, Score Higher", page2.heading)
        assertEquals(
            "Study with structured concepts, textbook solutions, and authentic board exam material.",
            page2.description
        )
        assertEquals(4, page2.features.size)

        val titles = page2.features.map { it.title }
        assertEquals("Chapter-wise Notes", titles[0])
        assertEquals("Practice MCQs", titles[1])
        assertEquals("Solved Exercises", titles[2])
        assertEquals("Past Papers", titles[3])

        assertEquals("Clear & concise concepts", page2.features[0].subtitle)
        assertEquals("Instant answers & logic", page2.features[1].subtitle)
        assertEquals("Textbook questions solved", page2.features[2].subtitle)
        assertEquals("Board exam patterns", page2.features[3].subtitle)
    }

    @Test
    fun page3_contentMatchesSpecification() {
        val page3 = OnboardingPageData.pages[2]
        assertEquals("Practice Smart with MCQs", page3.heading)
        assertEquals(
            "Test your exam readiness with interactive quizzes and real-time performance analytics.",
            page3.description
        )
        assertEquals(4, page3.features.size)

        val titles = page3.features.map { it.title }
        assertEquals("Custom Quizzes", titles[0])
        assertEquals("New Questions Every Time", titles[1])
        assertEquals("Track Your Progress", titles[2])
        assertEquals("Exam Ready", titles[3])

        assertEquals("Practice topic by topic", page3.features[0].subtitle)
        assertEquals("Varied question sets", page3.features[1].subtitle)
        assertEquals("Detailed performance stats", page3.features[2].subtitle)
        assertEquals("Boost exam confidence", page3.features[3].subtitle)
    }

    @Test
    fun allFeatures_haveValidIconsAndNonEmptyStrings() {
        OnboardingPageData.pages.forEach { page ->
            assertTrue("Heading must not be blank", page.heading.isNotBlank())
            assertTrue("Description must not be blank", page.description.isNotBlank())
            assertEquals("Each page must have exactly 4 feature items", 4, page.features.size)
            page.features.forEach { feature ->
                assertTrue("Feature title must not be blank", feature.title.isNotBlank())
                assertTrue("Feature subtitle must not be blank", feature.subtitle.isNotBlank())
                assertNotNull("Feature icon must not be null", feature.icon)
            }
        }
    }

    @Test
    fun preferences_constantsAreValid() {
        assertEquals("esify_onboarding_preferences", OnboardingPreferences.PREFS_NAME)
        assertEquals("key_onboarding_completed", OnboardingPreferences.KEY_ONBOARDING_COMPLETED)
    }
}
