package com.example.esify.ui.auth

import androidx.compose.ui.graphics.Color
import com.example.esify.navigation.BottomNavItem
import com.example.esify.navigation.Screen
import com.example.esify.ui.theme.AuthCardBorder
import com.example.esify.ui.theme.AuthDarkGreen
import com.example.esify.ui.theme.AuthMidGreen
import com.example.esify.ui.theme.AuthMintSurface
import com.example.esify.ui.theme.AuthSecondaryText
import com.example.esify.ui.theme.MintAccent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthChoiceScreenTest {

    @Test
    fun authRoutes_existAndMatchExpectedPaths() {
        assertEquals("auth_choice", Screen.AuthChoice.route)
        assertEquals("login", Screen.Login.route)
        assertEquals("signup", Screen.SignUp.route)
    }

    @Test
    fun authColorTokens_matchDesignSpecification() {
        assertEquals(Color(0xFF0B4A33), AuthDarkGreen)
        assertEquals(Color(0xFF14654A), AuthMidGreen)
        assertEquals(Color(0xFF63E6A6), MintAccent)
        assertEquals(Color(0xFFE3F2E9), AuthMintSurface)
        assertEquals(Color(0xFFE3EBE6), AuthCardBorder)
        assertEquals(Color(0xFF5F6D66), AuthSecondaryText)
    }

    @Test
    fun unauthenticatedRoutes_areExcludedFromBottomBar() {
        val hideBottomBarRoutes = setOf(
            Screen.Onboarding.route,
            Screen.AuthChoice.route,
            Screen.Login.route,
            Screen.SignUp.route
        )

        // None of the bottom bar destinations should be in the hidden routes set
        BottomNavItem.entries.forEach { item ->
            assertFalse(
                "Bottom bar item ${item.label} should not be hidden",
                hideBottomBarRoutes.contains(item.route)
            )
        }

        assertTrue(hideBottomBarRoutes.contains(Screen.AuthChoice.route))
        assertTrue(hideBottomBarRoutes.contains(Screen.Login.route))
        assertTrue(hideBottomBarRoutes.contains(Screen.SignUp.route))
        assertTrue(hideBottomBarRoutes.contains(Screen.Onboarding.route))
    }
}
