package com.android.boilerplate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.android.boilerplate.common.workers.PeriodicWorkerUtils
import com.android.boilerplate.core.base.BaseActivity
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.languages.navigation.languagesScreen
import com.android.boilerplate.features.languages.navigation.navigateToLanguages
import com.android.boilerplate.features.main.navigation.MAIN_ROUTE
import com.android.boilerplate.features.main.navigation.mainScreen
import com.android.boilerplate.features.themes.navigation.navigateToThemes
import com.android.boilerplate.features.themes.navigation.themesScreen
import com.android.boilerplate.features.themes.ui.ThemesUiState
import com.android.boilerplate.features.themes.ui.ThemesViewModel
import com.android.boilerplate.features.userdetails.navigation.navigateToUserDetails
import com.android.boilerplate.features.userdetails.navigation.userDetailsScreen
import com.android.boilerplate.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 02/05/2024
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var preferences: Preferences

    @Inject
    lateinit var activityContextProvider: ActivityContextProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            // set activity context for usage in repositories
            activityContextProvider.setActivity(this)
            // observe theme change
            var userSelectedTheme = -1
            val themesViewModel: ThemesViewModel by viewModels()
            val themesUiState = themesViewModel.state.collectAsStateWithLifecycle().value
            if (themesUiState is ThemesUiState.Success && themesUiState.data.isNotEmpty()) {
                userSelectedTheme = themesUiState.data.first { it.selected }.id
            }
            if (userSelectedTheme == -1) {
                userSelectedTheme = preferences.getInt(Preferences.KEY_THEME)
                if (userSelectedTheme == -1) {
                    // 0 is the system default theme
                    userSelectedTheme = 0
                }
            }
            ApplicationTheme(userSelectedTheme = userSelectedTheme) {
                App()
            }
        }
        setDefaultPreferences()
    }

    private fun setDefaultPreferences() {
        if (!preferences.contains(Preferences.KEY_NOTIFICATION)) {
            preferences.setBoolean(Preferences.KEY_NOTIFICATION, true)
            preferences.setInt(Preferences.KEY_THEME, 0)
            preferences.setString(Preferences.KEY_LANG, "en")
            PeriodicWorkerUtils.createPeriodicWorker(applicationContext)
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MAIN_ROUTE) {
        mainScreen(
            onUserClick = { navController.navigateToUserDetails(userId = it) },
            navigateToThemes = { navController.navigateToThemes() },
            navigateToLanguages = { navController.navigateToLanguages() }
        )
        userDetailsScreen(onNavigateBack = { navController.popBackStack() })
        themesScreen(onNavigateBack = { navController.popBackStack() })
        languagesScreen(onNavigateBack = { navController.popBackStack() })
    }
}