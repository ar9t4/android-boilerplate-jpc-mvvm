package com.android.boilerplate.features.users.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.users.ui.UsersRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val USERS_ROUTE = "users_route"

fun NavController.navigateToUsers(navOptions: NavOptions) =
    navigate(route = USERS_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.usersScreen(onUserClick: (userId: String) -> Unit) {
    composable(route = USERS_ROUTE) {
        UsersRoute(onUserClick = onUserClick)
    }
}