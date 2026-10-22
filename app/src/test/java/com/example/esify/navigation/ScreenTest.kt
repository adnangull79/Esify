package com.example.esify.navigation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ScreenTest {

    @Test
    fun screenRoutes_areUnique() {
        val routes = listOf(
            Screen.Onboarding.route,
            Screen.Home.route,
            Screen.Subjects.route,
            Screen.Downloads.route,
            Screen.Profile.route,
            Screen.SubjectDetails.route,
            Screen.ChapterDetails.route,
            Screen.TopicPractice.route
        )
        val distinctRoutes = routes.distinct()
        assertEquals("Each destination must have a unique route", routes.size, distinctRoutes.size)
    }

    @Test
    fun startDestination_isHome() {
        assertEquals("home", Screen.Home.route)
    }

    @Test
    fun onboardingRoute_isExpected() {
        assertEquals("onboarding", Screen.Onboarding.route)
    }

    @Test
    fun screenRoutes_matchExpectedValues() {
        assertEquals("onboarding", Screen.Onboarding.route)
        assertEquals("home", Screen.Home.route)
        assertEquals("subjects", Screen.Subjects.route)
        assertEquals("downloads", Screen.Downloads.route)
        assertEquals("profile", Screen.Profile.route)
        assertEquals("subject_details/{subjectId}", Screen.SubjectDetails.route)
        assertEquals("chapter_details/{subjectId}/{chapterId}", Screen.ChapterDetails.route)
        assertEquals("topic_practice/{subjectId}/{chapterId}/{topicId}/{initialMode}", Screen.TopicPractice.route)
    }

    @Test
    fun subjectDetails_createRouteConstructsExpectedPath() {
        assertEquals("subject_details/computer_science", Screen.SubjectDetails.createRoute("computer_science"))
        assertEquals("subject_details/physics", Screen.SubjectDetails.createRoute("physics"))
    }

    @Test
    fun chapterDetails_createRouteConstructsExpectedPath() {
        assertEquals("chapter_details/computer_science/cs_ch1", Screen.ChapterDetails.createRoute("computer_science", "cs_ch1"))
        assertEquals("chapter_details/physics/phy_ch2", Screen.ChapterDetails.createRoute("physics", "phy_ch2"))
    }

    @Test
    fun topicPractice_createRouteConstructsExpectedPath() {
        assertEquals(
            "topic_practice/computer_science/cs_ch1/cs_ch1_t1/mcq",
            Screen.TopicPractice.createRoute("computer_science", "cs_ch1", "cs_ch1_t1")
        )
        assertEquals(
            "topic_practice/computer_science/cs_ch1/cs_ch1_t1/short_questions",
            Screen.TopicPractice.createRoute("computer_science", "cs_ch1", "cs_ch1_t1", "short_questions")
        )
        assertEquals(
            "topic_practice/physics/phy_ch1/phy_ch1_t1/quiz",
            Screen.TopicPractice.createRoute("physics", "phy_ch1", "phy_ch1_t1", "quiz")
        )
    }

    @Test
    fun bottomNavItems_countAndRoutesMatchScreens() {
        val items = BottomNavItem.entries
        assertEquals("There must be exactly 4 bottom nav items", 4, items.size)

        assertEquals(Screen.Home.route, BottomNavItem.HOME.route)
        assertEquals(Screen.Subjects.route, BottomNavItem.SUBJECTS.route)
        assertEquals(Screen.Downloads.route, BottomNavItem.DOWNLOADS.route)
        assertEquals(Screen.Profile.route, BottomNavItem.PROFILE.route)

        assertEquals("Home", BottomNavItem.HOME.label)
        assertEquals("Subjects", BottomNavItem.SUBJECTS.label)
        assertEquals("Downloads", BottomNavItem.DOWNLOADS.label)
        assertEquals("Profile", BottomNavItem.PROFILE.label)

        items.forEach { item ->
            assertNotNull(item.selectedIcon)
            assertNotNull(item.unselectedIcon)
        }
    }
}
