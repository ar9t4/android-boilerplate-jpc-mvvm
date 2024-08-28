package com.android.boilerplate.features.themes.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.features.themes.domain.model.Theme

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface ThemesActions : BaseAction {
    data object GetThemeItems: ThemesActions
    data class OnThemeClick(val id: Int): ThemesActions
}

sealed interface ThemesEvents : BaseEvent {
    data object Default: ThemesEvents
}

sealed interface ThemesUiState : BaseUiState {
    data object Default: ThemesUiState
    data object Loading : ThemesUiState
    data class Error(val statusCode: Int = -1, val message: String) : ThemesUiState
    data class Success(val data: List<Theme>) : ThemesUiState
}