package com.android.boilerplate.features.userdetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.userdetails.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel<UserDetailsActions, UserDetailsEvents, UserDetailsUiState>(
    initialEvent = UserDetailsEvents.Default,
    initialState = UserDetailsUiState.Default
) {
    private val userId: String = savedStateHandle["userId"] ?: ""

    override fun processAction(action: UserDetailsActions) {
        when (action) {
            is UserDetailsActions.GetUser -> getUser(id = userId.toInt())
        }
    }

    private fun getUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase(params = id)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = UserDetailsUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(UserDetailsUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { user ->
                                updateState(state = UserDetailsUiState.Success(data = user))
                            }
                        }
                    }
                }
        }
    }
}