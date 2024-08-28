package com.android.boilerplate.features.more.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.more.ui.MoreRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val MORE_ROUTE = "more_route"

fun NavController.navigateToMore(navOptions: NavOptions) =
    navigate(route = MORE_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.moreScreen(navigateTo: (route: String) -> Unit) {
    composable(route = MORE_ROUTE) {
        MoreRoute(navigateTo = { navigateTo(it) })
    }
}