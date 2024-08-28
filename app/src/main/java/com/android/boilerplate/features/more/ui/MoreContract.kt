package com.android.boilerplate.features.more.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.features.more.domain.model.More

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface MoreActions : BaseAction {
    data object GetMoreItems: MoreActions
    data class OnItemClick(val item: More): MoreActions
}

sealed interface MoreEvents : BaseEvent {
    data object Default: MoreEvents
    data object NavigateToSettings: MoreEvents
    data object NavigateToFeedback: MoreEvents
    data object NavigateToPrivacyPolicy: MoreEvents
    data object NavigateToShare: MoreEvents
    data object NavigateToRateUs: MoreEvents
    data object NavigateToMoreApps: MoreEvents
    data object NavigateToVersion: MoreEvents
}

sealed interface MoreUiState : BaseUiState {
    data object Default: MoreUiState
    data object Loading : MoreUiState
    data class Error(val statusCode: Int = -1, val message: String) : MoreUiState
    data class Success(val data: List<More>) : MoreUiState
}