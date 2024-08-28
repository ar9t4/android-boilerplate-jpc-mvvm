package com.android.boilerplate.features.themes.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.themes.domain.usecase.GetThemeItemsUseCase
import com.android.boilerplate.features.themes.domain.usecase.SetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val getThemeItemsUseCase: GetThemeItemsUseCase,
    private val setThemeUseCase: SetThemeUseCase,
) : BaseViewModel<ThemesActions, ThemesEvents, ThemesUiState>(
    initialEvent = ThemesEvents.Default,
    initialState = ThemesUiState.Default
) {

    override fun processAction(action: ThemesActions) {
        when (action) {
            is ThemesActions.GetThemeItems -> getThemeItems()
            is ThemesActions.OnThemeClick -> setTheme(id = action.id)
        }
    }

    private fun getThemeItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getThemeItemsUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = ThemesUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(ThemesUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { items ->
                                updateState(state = ThemesUiState.Success(data = items))
                            }
                        }
                    }
                }
        }
    }

    private fun setTheme(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setThemeUseCase(params = id)
                .onSuccess { getThemeItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = ThemesUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }
}