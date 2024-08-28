package com.android.boilerplate.features.languages.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.languages.ui.LanguagesRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val LANGUAGES_ROUTE = "languages_route"

fun NavController.navigateToLanguages(navOptions: NavOptions = NavOptions.Builder().build()) =
    navigate(route = LANGUAGES_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.languagesScreen(onNavigateBack: () -> Unit) {
    composable(route = LANGUAGES_ROUTE) {
        LanguagesRoute(onNavigateBack = onNavigateBack)
    }
}