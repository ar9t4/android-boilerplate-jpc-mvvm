package com.android.boilerplate.features.feedback.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.feedback.domain.model.Feedback
import com.android.boilerplate.features.feedback.domain.usecase.GetFeedbackItemsUseCase
import com.android.boilerplate.features.feedback.domain.usecase.ResetFeedbackItemsUseCase
import com.android.boilerplate.features.feedback.domain.usecase.SelectDeselectFeedbackItemUseCase
import com.android.boilerplate.features.feedback.domain.usecase.ValidateFeedbackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val getFeedbackItemsUseCase: GetFeedbackItemsUseCase,
    private val selectDeselectFeedbackItemUseCase: SelectDeselectFeedbackItemUseCase,
    private val validateFeedbackUseCase: ValidateFeedbackUseCase,
    private val resetFeedbackItemsUseCase: ResetFeedbackItemsUseCase
) : BaseViewModel<FeedbackActions, FeedbackEvents, FeedbackUiState>(
    initialEvent = FeedbackEvents.Default,
    initialState = FeedbackUiState.Default
) {

    override fun processAction(action: FeedbackActions) {
        when (action) {
            is FeedbackActions.GetFeedbackItems -> getFeedbackItems()
            is FeedbackActions.OnItemClick -> selectDeselectFeedbackItem(action.id)
            is FeedbackActions.ValidateFeedback -> validateFeedback(action.items, action.feedback)
            is FeedbackActions.ResetFeedbackItems -> resetFeedbackItems()
        }
    }

    private fun getFeedbackItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getFeedbackItemsUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = FeedbackUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(FeedbackUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { items ->
                                updateState(state = FeedbackUiState.Success(data = items))
                            }
                        }
                    }
                }
        }
    }

    private fun selectDeselectFeedbackItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            selectDeselectFeedbackItemUseCase(params = id)
                .onSuccess { getFeedbackItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = FeedbackUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }

    private fun validateFeedback(items: List<Feedback>, feedback: String) {
        viewModelScope.launch(Dispatchers.IO) {
            validateFeedbackUseCase(params = Pair(items, feedback))
                .onSuccess {
                    if (!it) {
                        emitEvent(FeedbackEvents.InvalidFeedback)
                    } else {
                        emitEvent(FeedbackEvents.FeedbackValidated(items, feedback))
                    }
                }
                .onFailure { e ->
                    // update error state
                    updateState(state = FeedbackUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }

    private fun resetFeedbackItems() {
        viewModelScope.launch(Dispatchers.IO) {
            resetFeedbackItemsUseCase(params = NoParams)
                .onSuccess { getFeedbackItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = FeedbackUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }
}