package com.android.boilerplate.features.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.boilerplate.R
import com.android.boilerplate.common.components.Loader
import com.android.boilerplate.features.settings.ui.components.SettingItemsList

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun SettingsRoute(
    navigateToThemes: () -> Unit,
    navigateToLanguages: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()) {
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    when (events) {
        is SettingsEvents.Default -> {}
        is SettingsEvents.NavigateToThemes -> navigateToThemes()
        is SettingsEvents.NavigateToLanguages -> navigateToLanguages()
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(SettingsActions.GetSettingItems)
    }
    SettingsScreen(
        uiState = uiState,
        onSettingClick = { viewModel.processAction(SettingsActions.OnSettingClick(settingId = it)) }
    )
}

@Composable
private fun SettingsScreen(uiState: SettingsUiState, onSettingClick: (settingId: Int) -> Unit) {
    when (uiState) {
        is SettingsUiState.Default -> {}
        is SettingsUiState.Loading -> {
            Loader(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onSurface,
                trackColor = MaterialTheme.colorScheme.surface
            )
        }

        is SettingsUiState.Error -> {}
        is SettingsUiState.Success -> {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings_filled),
                        contentDescription = stringResource(
                            id = R.string.item_icon,
                            stringResource(id = R.string.settings)
                        ),
                        modifier = Modifier
                            .width(width = 36.dp)
                            .height(height = 36.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                SettingItemsList(items = uiState.data, onSettingClick = { onSettingClick(it) })
            }
        }
    }
}