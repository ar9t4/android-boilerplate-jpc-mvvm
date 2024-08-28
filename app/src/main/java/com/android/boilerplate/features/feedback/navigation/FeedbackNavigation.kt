package com.android.boilerplate.features.feedback.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.android.boilerplate.features.feedback.ui.FeedbackRoute

/**
 * Created by Abdul Rahman on 08/05/2024
 */
const val FEEDBACK_ROUTE = "feedback_route"

fun NavController.navigateToFeedback(navOptions: NavOptions) =
    navigate(route = FEEDBACK_ROUTE, navOptions = navOptions)

fun NavGraphBuilder.feedbackScreen() {
    composable(route = FEEDBACK_ROUTE) {
        FeedbackRoute()
    }
}