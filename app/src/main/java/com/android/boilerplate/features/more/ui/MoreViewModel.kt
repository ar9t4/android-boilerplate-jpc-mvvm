package com.android.boilerplate.features.more.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.more.domain.usecase.GetMoreItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class MoreViewModel @Inject constructor(
    private val getMoreItemsUseCase: GetMoreItemsUseCase
) : BaseViewModel<MoreActions, MoreEvents, MoreUiState>(
    initialEvent = MoreEvents.Default,
    initialState = MoreUiState.Default
) {

    override fun processAction(action: MoreActions) {
        when (action) {
            is MoreActions.GetMoreItems -> getMoreItems()
            is MoreActions.OnItemClick -> {
                when (action.item.id) {
                    1 -> emitEvent(MoreEvents.NavigateToSettings)
                    2 -> emitEvent(MoreEvents.NavigateToFeedback)
                    3 -> emitEvent(MoreEvents.NavigateToPrivacyPolicy)
                    4 -> emitEvent(MoreEvents.NavigateToShare)
                    5 -> emitEvent(MoreEvents.NavigateToRateUs)
                    6 -> emitEvent(MoreEvents.NavigateToMoreApps)
                    7 -> emitEvent(MoreEvents.NavigateToVersion)
                }
            }
        }
    }

    private fun getMoreItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getMoreItemsUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = MoreUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(state = MoreUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { items ->
                                updateState(state = MoreUiState.Success(data = items))
                            }
                        }
                    }
                }
        }
    }
}