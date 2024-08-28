package com.android.boilerplate.features.languages.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.features.languages.domain.model.Language

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface LanguagesActions : BaseAction {
    data object GetLanguageItems : LanguagesActions
    data class OnLanguageClick(val selectedLanguage: Language): LanguagesActions
}

sealed interface LanguagesEvents : BaseEvent {
    data object Default : LanguagesEvents
}

sealed interface LanguagesUiState : BaseUiState {
    data object Default : LanguagesUiState
    data object Loading : LanguagesUiState
    data class Error(val statusCode: Int = -1, val message: String) : LanguagesUiState
    data class Success(val data: List<Language>) : LanguagesUiState
}