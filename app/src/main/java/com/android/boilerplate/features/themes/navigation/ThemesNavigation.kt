package com.android.boilerplate.features.themes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.themes.ui.ThemesRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val THEMES_ROUTE = "themes_route"

fun NavController.navigateToThemes(navOptions: NavOptions = NavOptions.Builder().build()) =
    navigate(route = THEMES_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.themesScreen(onNavigateBack: () -> Unit) {
    composable(route = THEMES_ROUTE) {
        ThemesRoute(onNavigateBack = onNavigateBack)
    }
}