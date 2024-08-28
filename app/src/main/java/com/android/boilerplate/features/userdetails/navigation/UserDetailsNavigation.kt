package com.android.boilerplate.features.userdetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.boilerplate.features.userdetails.ui.UserDetailsRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val USER_ID_ARG = "userId"
const val USER_DETAILS_ROUTE_BASE = "user_details_route"
const val USER_DETAILS_ROUTE = "$USER_DETAILS_ROUTE_BASE/{$USER_ID_ARG}"

fun NavController.navigateToUserDetails(
    userId: String,
    navOptions: NavOptions = NavOptions.Builder().build()
) = navigate(route = "$USER_DETAILS_ROUTE_BASE/$userId", navOptions = navOptions)

fun NavGraphBuilder.userDetailsScreen(onNavigateBack: () -> Unit) {
    composable(
        route = USER_DETAILS_ROUTE,
        arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType })
    ) {
        UserDetailsRoute(onNavigateBack = onNavigateBack)
    }
}