package com.android.boilerplate.features.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.android.boilerplate.features.feedback.navigation.FEEDBACK_ROUTE
import com.android.boilerplate.features.feedback.navigation.feedbackScreen
import com.android.boilerplate.features.feedback.navigation.navigateToFeedback
import com.android.boilerplate.features.main.ui.components.BottomNavigationBar
import com.android.boilerplate.features.more.navigation.moreScreen
import com.android.boilerplate.features.settings.navigation.SETTINGS_ROUTE
import com.android.boilerplate.features.settings.navigation.navigateToSettings
import com.android.boilerplate.features.settings.navigation.settingsScreen
import com.android.boilerplate.features.users.navigation.USERS_ROUTE
import com.android.boilerplate.features.users.navigation.usersScreen

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun MainRoute(
    onUserClick: (userId: String) -> Unit,
    navigateToThemes: () -> Unit,
    navigateToLanguages: () -> Unit
) {
    MainScreen(
        onUserClick = onUserClick,
        navigateToThemes = navigateToThemes,
        navigateToLanguages = navigateToLanguages
    )
}

@Composable
private fun MainScreen(
    onUserClick: (userId: String) -> Unit,
    navigateToThemes: () -> Unit,
    navigateToLanguages: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavHost(navController, USERS_ROUTE, Modifier.padding(it)) {
            usersScreen(onUserClick = onUserClick)
            settingsScreen(
                navigateToThemes = navigateToThemes,
                navigateToLanguages = navigateToLanguages
            )
            feedbackScreen()
            moreScreen(navigateTo = { route ->
                val navOptions = NavOptions
                    .Builder()
                    .setPopUpTo(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = true,
                        saveState = true
                    )
                    .setLaunchSingleTop(true)
                    .setRestoreState(true)
                    .build()
                navController.apply {
                    when (route) {
                        SETTINGS_ROUTE -> navigateToSettings(navOptions = navOptions)
                        FEEDBACK_ROUTE -> navigateToFeedback(navOptions = navOptions)
                    }
                }
            })
        }
    }
}