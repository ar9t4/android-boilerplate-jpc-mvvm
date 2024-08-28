package com.android.boilerplate.features.main.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.boilerplate.R
import com.android.boilerplate.features.feedback.navigation.FEEDBACK_ROUTE
import com.android.boilerplate.features.more.navigation.MORE_ROUTE
import com.android.boilerplate.features.settings.navigation.SETTINGS_ROUTE
import com.android.boilerplate.features.users.navigation.USERS_ROUTE

/**
 * Created by Abdul Rahman on 04/06/2024
 */
internal data class NavigationItem(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val unfilled: Int,
    @DrawableRes val filled: Int,
) {
    companion object {
        fun getItems(): List<NavigationItem> {
            return listOf(
                NavigationItem(
                    USERS_ROUTE,
                    R.string.users,
                    R.drawable.ic_users,
                    R.drawable.ic_users_filled
                ),
                NavigationItem(
                    SETTINGS_ROUTE,
                    R.string.settings,
                    R.drawable.ic_settings,
                    R.drawable.ic_settings_filled
                ),
                NavigationItem(
                    FEEDBACK_ROUTE,
                    R.string.feedback,
                    R.drawable.ic_feedback,
                    R.drawable.ic_feedback_filled
                ),
                NavigationItem(
                    MORE_ROUTE,
                    R.string.more,
                    R.drawable.ic_more,
                    R.drawable.ic_more_filled
                )
            )
        }
    }
}