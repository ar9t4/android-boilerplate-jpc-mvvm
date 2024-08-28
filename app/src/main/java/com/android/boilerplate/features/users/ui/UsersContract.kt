package com.android.boilerplate.features.users.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 23/05/2024
 */
sealed interface UsersActions : BaseAction {
    data object GetUsers : UsersActions
    data class OnItemClick(val userId: String) : UsersActions
    data class OnRefresh(val results: Int) : UsersActions
}

sealed interface UsersEvents : BaseEvent {
    data object Default : UsersEvents
    data class NavigateToUserDetails(val userId: String) : UsersEvents
}

sealed interface UsersUiState : BaseUiState {
    data object Default : UsersUiState
    data object Loading : UsersUiState
    data class Error(val statusCode: Int = -1, val message: String) : UsersUiState
    data class Success(val data: List<RandomUser>) : UsersUiState
    data class Refresh(val currentData: List<RandomUser>) : UsersUiState
}