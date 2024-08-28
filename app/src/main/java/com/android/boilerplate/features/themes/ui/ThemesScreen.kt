package com.android.boilerplate.features.themes.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.common.components.TopBar
import com.android.boilerplate.features.settings.ui.SettingsActions
import com.android.boilerplate.features.settings.ui.SettingsViewModel
import com.android.boilerplate.features.themes.ui.components.ThemeItemsList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun ThemesRoute(
    onNavigateBack: () -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    viewModel: ThemesViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val coroutineScope = rememberCoroutineScope()
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    when (events) {
        is ThemesEvents.Default -> {}
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(ThemesActions.GetThemeItems)
    }
    ThemesScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onItemClick = {
            viewModel.processAction(ThemesActions.OnThemeClick(id = it))
            coroutineScope.launch {
                delay(100)
                settingsViewModel.processAction(SettingsActions.UpdateSelectedTheme)
            }
        }
    )
}

@Composable
private fun ThemesScreen(
    uiState: ThemesUiState,
    onNavigateBack: () -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(id = R.string.select_app_theme),
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        when (uiState) {
            is ThemesUiState.Default -> {}
            is ThemesUiState.Loading -> {
                Loader(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    color = MaterialTheme.colorScheme.onSurface,
                    trackColor = MaterialTheme.colorScheme.surface
                )
            }

            is ThemesUiState.Error -> {}
            is ThemesUiState.Success -> {
                ThemeItemsList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        ),
                    items = uiState.data,
                    onItemClick = onItemClick
                )
            }
        }
    }
}