package com.android.boilerplate.features.userdetails.ui

import com.android.boilerplate.core.base.BaseAction
import com.android.boilerplate.core.base.BaseEvent
import com.android.boilerplate.core.base.BaseUiState
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 14/05/2024
 */
sealed interface UserDetailsActions : BaseAction {
    data object GetUser : UserDetailsActions
}

sealed interface UserDetailsEvents : BaseEvent {
    data object Default : UserDetailsEvents
}

sealed interface UserDetailsUiState : BaseUiState {
    data object Default : UserDetailsUiState
    data object Loading : UserDetailsUiState
    data class Error(val statusCode: Int = -1, val message: String) : UserDetailsUiState
    data class Success(val data: RandomUser) : UserDetailsUiState
}