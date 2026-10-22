package com.example.esify.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val EsifyLightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    onPrimary = WhiteCards,
    primaryContainer = LightMint,
    onPrimaryContainer = DarkForestGreen,
    secondary = MidGreen,
    onSecondary = WhiteCards,
    secondaryContainer = LightMint,
    onSecondaryContainer = DarkForestGreen,
    tertiary = PurpleAccent,
    onTertiary = WhiteCards,
    background = LightBackground,
    onBackground = PrimaryText,
    surface = WhiteCards,
    onSurface = PrimaryText,
    surfaceVariant = LightMint,
    onSurfaceVariant = SecondaryText,
    outline = OutlineDivider,
    outlineVariant = ProgressTrack
)

@Composable
fun EsifyTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = EsifyLightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = true
                insetsController.isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}