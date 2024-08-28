package com.android.boilerplate.features.languages.ui

import androidx.lifecycle.viewModelScope
import com.android.boilerplate.core.base.BaseViewModel
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.features.languages.domain.model.Language
import com.android.boilerplate.features.languages.domain.usecase.GetLanguageItemsUseCase
import com.android.boilerplate.features.languages.domain.usecase.SetLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val getLanguageItemsUseCase: GetLanguageItemsUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : BaseViewModel<LanguagesActions, LanguagesEvents, LanguagesUiState>(
    initialEvent = LanguagesEvents.Default,
    initialState = LanguagesUiState.Default
) {

    override fun processAction(action: LanguagesActions) {
        when (action) {
            is LanguagesActions.GetLanguageItems -> getLanguageItems()
            is LanguagesActions.OnLanguageClick ->
                setLanguage(selectedLanguage = action.selectedLanguage)
        }
    }

    private fun getLanguageItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getLanguageItemsUseCase(params = NoParams)
                .collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            // update loading state
                            updateState(state = LanguagesUiState.Loading)
                        }

                        is Resource.Error -> {
                            // update error state
                            if (it.statusCode != null && it.message != null) {
                                updateState(LanguagesUiState.Error(it.statusCode, it.message))
                            }
                        }

                        is Resource.Success -> {
                            // update success state
                            it.data?.let { items ->
                                updateState(state = LanguagesUiState.Success(data = items))
                            }
                        }
                    }
                }
        }
    }

    private fun setLanguage(selectedLanguage: Language) {
        viewModelScope.launch(Dispatchers.IO) {
            setLanguageUseCase(params = selectedLanguage)
                .onSuccess { getLanguageItems() }
                .onFailure { e ->
                    // update error state
                    updateState(state = LanguagesUiState.Error(message = e.message ?: e.toString()))
                }
        }
    }
}