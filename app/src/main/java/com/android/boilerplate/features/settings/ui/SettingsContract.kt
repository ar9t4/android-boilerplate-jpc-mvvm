package com.android.boilerplate.features.settings.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.features.settings.domain.model.Setting

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface SettingsActions : BaseAction {
    data object GetSettingItems: SettingsActions
    data class OnSettingClick(val settingId: Int): SettingsActions
    data object UpdateSelectedTheme: SettingsActions
    data object UpdateSelectedLanguage: SettingsActions
}

sealed interface SettingsEvents : BaseEvent {
    data object Default: SettingsEvents
    data object NavigateToThemes: SettingsEvents
    data object NavigateToLanguages: SettingsEvents
}

sealed interface SettingsUiState : BaseUiState {
    data object Default: SettingsUiState
    data object Loading : SettingsUiState
    data class Error(val statusCode: Int = -1, val message: String) : SettingsUiState
    data class Success(val data: List<Setting>) : SettingsUiState
}