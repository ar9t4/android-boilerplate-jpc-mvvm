package com.android.boilerplate.ui.theme

import android.app.Activity
import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Created by Abdul Rahman on 02/05/2024
 */
// dark color scheme
private val darkColorScheme = darkColorScheme(
    primary = darkSurfaceContainer,
    onPrimary = darkOnSurfaceContainer,
    primaryContainer = darkSurfaceContainer,
    onPrimaryContainer = darkOnSurfaceContainer,

    secondary = darkSurfaceContainer,
    onSecondary = darkOnSurfaceContainer,
    secondaryContainer = darkSurfaceContainer,
    onSecondaryContainer = darkOnSurfaceContainer,

    tertiary = darkSurfaceContainer,
    onTertiary = darkOnSurfaceContainer,
    tertiaryContainer = darkSurfaceContainer,
    onTertiaryContainer = darkOnSurfaceContainer,

    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,

    surface = darkSurface,
    onSurface = darkOnSurface,
    surfaceContainer = darkSurfaceContainer,

    background = darkSurface,
    onBackground = darkOnSurface
)

// light color scheme
private val lightColorScheme = lightColorScheme(
    primary = lightSurfaceContainer,
    onPrimary = lightOnSurfaceContainer,
    primaryContainer = lightSurfaceContainer,
    onPrimaryContainer = lightOnSurfaceContainer,

    secondary = lightSurfaceContainer,
    onSecondary = lightOnSurfaceContainer,
    secondaryContainer = lightSurfaceContainer,
    onSecondaryContainer = lightOnSurfaceContainer,

    tertiary = lightSurfaceContainer,
    onTertiary = lightOnSurfaceContainer,
    tertiaryContainer = lightSurfaceContainer,
    onTertiaryContainer = lightOnSurfaceContainer,

    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,

    surface = lightSurface,
    onSurface = lightOnSurface,
    surfaceContainer = lightSurfaceContainer,

    background = lightSurface,
    onBackground = lightOnSurface
)

@Composable
fun ApplicationTheme(
    userSelectedTheme: Int = 0,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }*/
    val isAppearanceLightStatusBars: Boolean
    val colorScheme = when (userSelectedTheme) {
        0 -> {
            if (darkTheme) {
                isAppearanceLightStatusBars = false
                darkColorScheme
            } else {
                isAppearanceLightStatusBars = true
                lightColorScheme
            }
        }
        1 -> {
            isAppearanceLightStatusBars = true
            lightColorScheme
        }
        else -> {
            isAppearanceLightStatusBars = false
            darkColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.TRANSPARENT
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                isAppearanceLightStatusBars
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}