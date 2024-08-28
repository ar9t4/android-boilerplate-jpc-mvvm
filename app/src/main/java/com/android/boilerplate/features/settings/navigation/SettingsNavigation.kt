package com.android.boilerplate.features.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.settings.ui.SettingsRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val SETTINGS_ROUTE = "settings_route"

fun NavController.navigateToSettings(navOptions: NavOptions) =
    navigate(route = SETTINGS_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.settingsScreen(
    navigateToThemes: () -> Unit,
    navigateToLanguages: () -> Unit
) {
    composable(route = SETTINGS_ROUTE) {
        SettingsRoute(
            navigateToThemes = navigateToThemes,
            navigateToLanguages = navigateToLanguages
        )
    }
}