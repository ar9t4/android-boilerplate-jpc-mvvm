package com.android.boilerplate.features.languages.ui

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
import com.android.boilerplate.common.utils.getActivity
import com.android.boilerplate.features.languages.domain.model.Language
import com.android.boilerplate.features.languages.ui.components.LanguageItemsList
import com.android.boilerplate.features.settings.ui.SettingsActions
import com.android.boilerplate.features.settings.ui.SettingsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Abdul Rahman on 08/05/2024
 */
@Composable
internal fun LanguagesRoute(
    onNavigateBack: () -> Unit,
    viewModel: LanguagesViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val events = viewModel.event.collectAsStateWithLifecycle().value
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    when (events) {
        is LanguagesEvents.Default -> {}
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.processAction(LanguagesActions.GetLanguageItems)
    }
    LanguagesScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onItemClick = {
            viewModel.processAction(LanguagesActions.OnLanguageClick(selectedLanguage = it))
            coroutineScope.launch {
                delay(100)
                settingsViewModel.processAction(SettingsActions.UpdateSelectedLanguage)
                // language has been changed so recreate activity after some delay
                context.getActivity()?.recreate()
            }
        }
    )
}

@Composable
private fun LanguagesScreen(
    uiState: LanguagesUiState,
    onNavigateBack: () -> Unit,
    onItemClick: (item: Language) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = stringResource(id = R.string.select_app_language),
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        when (uiState) {
            is LanguagesUiState.Default -> {}
            is LanguagesUiState.Loading -> {
                Loader(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    color = MaterialTheme.colorScheme.onSurface,
                    trackColor = MaterialTheme.colorScheme.surface
                )
            }

            is LanguagesUiState.Error -> {}
            is LanguagesUiState.Success -> {
                LanguageItemsList(
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