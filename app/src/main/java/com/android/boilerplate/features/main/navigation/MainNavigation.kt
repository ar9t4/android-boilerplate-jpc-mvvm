package com.android.boilerplate.features.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.main.ui.MainRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val MAIN_ROUTE = "main_route"

fun NavController.navigateToMain(navOptions: NavOptions) =
    navigate(route = MAIN_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.mainScreen(
    onUserClick: (userId: String) -> Unit,
    navigateToThemes: () -> Unit,
    navigateToLanguages: () -> Unit
) {
    composable(route = MAIN_ROUTE) {
        MainRoute(
            onUserClick = onUserClick,
            navigateToThemes = navigateToThemes,
            navigateToLanguages = navigateToLanguages
        )
    }
}