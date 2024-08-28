package com.android.boilerplate.features.feedback.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.features.feedback.domain.model.Feedback

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface FeedbackActions : BaseAction {
    data object GetFeedbackItems: FeedbackActions
    data class OnItemClick(val id: Int): FeedbackActions
    data class ValidateFeedback(val items: List<Feedback>, val feedback: String): FeedbackActions
    data object ResetFeedbackItems: FeedbackActions
}

sealed interface FeedbackEvents : BaseEvent {
    data object Default: FeedbackEvents
    data object InvalidFeedback: FeedbackEvents
    data class FeedbackValidated(val items: List<Feedback>, val feedback: String): FeedbackEvents
}

sealed interface FeedbackUiState : BaseUiState {
    data object Default: FeedbackUiState
    data object Loading : FeedbackUiState
    data class Error(val statusCode: Int = -1, val message: String) : FeedbackUiState
    data class Success(val data: List<Feedback>) : FeedbackUiState
}