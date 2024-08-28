package com.android.boilerplate.features.users.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.users.domain.model.GetUsersRequest
import com.android.boilerplate.features.users.domain.usecase.DeleteAllUsersUseCase
import com.android.boilerplate.features.users.domain.usecase.GetLocalUsersUseCase
import com.android.boilerplate.features.users.domain.usecase.GetRemoteUsersUseCase
import com.android.boilerplate.features.users.domain.usecase.InsertUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getLocalUsersUseCase: GetLocalUsersUseCase,
    private val getRemoteUsersUseCase: GetRemoteUsersUseCase,
    private val insertUsersUseCase: InsertUsersUseCase,
    private val deleteAllUsersUseCase: DeleteAllUsersUseCase,
) : BaseViewModel<UsersActions, UsersEvents, UsersUiState>(
    initialEvent = UsersEvents.Default,
    initialState = UsersUiState.Default
) {

    override fun processAction(action: UsersActions) {
        when (action) {
            is UsersActions.GetUsers ->
                getLocalUsers()

            is UsersActions.OnItemClick ->
                emitEvent(UsersEvents.NavigateToUserDetails(action.userId))

            is UsersActions.OnRefresh ->
                refreshUsers(action.results)
        }
    }

    private fun getLocalUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocalUsersUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(state = UsersUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            it.data?.let { users ->
                                if (users.isNotEmpty()) {
                                    // update success state
                                    updateState(state = UsersUiState.Success(users))
                                } else {
                                    // get users from remote
                                    getRemoteUsers()
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun getRemoteUsers(results: Int = 20) {
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteUsersUseCase(params = GetUsersRequest(results = results))
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = UsersUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(state = UsersUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // insert new remote users into local database
                            it.data?.results?.let { users -> insertUsers(users = users) }
                        }
                    }
                }
        }
    }

    private fun insertUsers(users: List<RandomUser>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertUsersUseCase(params = users)
                .onSuccess {
                    // users have been inserted so get users from local database
                    getLocalUsers()
                }
                .onFailure { e ->
                    // update error state
                    updateState(state = UsersUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }

    private fun refreshUsers(results: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            // get new users from remote
            getRemoteUsersUseCase(params = GetUsersRequest(results = results))
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update refresh state
                            val currentState = state.value
                            val currentData = mutableListOf<RandomUser>()
                            if (currentState is UsersUiState.Success) {
                                currentData.addAll(currentState.data)
                            }
                            updateState(state = UsersUiState.Refresh(currentData))
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(state = UsersUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // delete all current users from local database
                            it.data?.results?.let { users -> deleteAllUsers(users = users) }
                        }
                    }
                }
        }
    }

    private fun deleteAllUsers(users: List<RandomUser>) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllUsersUseCase(params = NoParams)
                .onSuccess {
                    // insert new users into the database
                    insertUsers(users = users)
                }
                .onFailure { e ->
                    // update error state
                    updateState(state = UsersUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }
}