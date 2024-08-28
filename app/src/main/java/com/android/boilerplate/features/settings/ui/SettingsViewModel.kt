package com.android.boilerplate.features.settings.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.settings.domain.usecase.GetSettingItemsUseCase
import com.android.boilerplate.features.settings.domain.usecase.ToggleNotificationsStateUseCase
import com.android.boilerplate.features.settings.domain.usecase.UpdateSelectedLanguageUseCase
import com.android.boilerplate.features.settings.domain.usecase.UpdateSelectedThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingItemsUseCase: GetSettingItemsUseCase,
    private val toggleNotificationsStateUseCase: ToggleNotificationsStateUseCase,
    private val updateSelectedThemeUseCase: UpdateSelectedThemeUseCase,
    private val updateSelectedLanguageUseCase: UpdateSelectedLanguageUseCase
) : BaseViewModel<SettingsActions, SettingsEvents, SettingsUiState>(
    initialEvent = SettingsEvents.Default,
    initialState = SettingsUiState.Default
) {

    override fun processAction(action: SettingsActions) {
        when (action) {
            is SettingsActions.GetSettingItems -> getSettingsItems()
            is SettingsActions.OnSettingClick -> {
                when (action.settingId) {
                    0 -> toggleNotificationsState()
                    1 -> emitEvent(SettingsEvents.NavigateToThemes)
                    else -> emitEvent(SettingsEvents.NavigateToLanguages)
                }
            }

            is SettingsActions.UpdateSelectedTheme -> updateSelectedTheme()
            is SettingsActions.UpdateSelectedLanguage -> updateSelectedLanguage()
        }
    }

    private fun getSettingsItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getSettingItemsUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = SettingsUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(SettingsUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { items ->
                                updateState(state = SettingsUiState.Success(data = items))
                            }
                        }
                    }
                }
        }
    }

    private fun toggleNotificationsState() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(state = SettingsUiState.Loading)
            toggleNotificationsStateUseCase(params = NoParams)
                .onSuccess { getSettingsItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = SettingsUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }

    private fun updateSelectedTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(state = SettingsUiState.Loading)
            updateSelectedThemeUseCase(params = NoParams)
                .onSuccess { getSettingsItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = SettingsUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }

    private fun updateSelectedLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState(state = SettingsUiState.Loading)
            updateSelectedLanguageUseCase(params = NoParams)
                .onSuccess { getSettingsItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = SettingsUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }
}