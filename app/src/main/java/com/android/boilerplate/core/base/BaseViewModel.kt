package com.android.boilerplate.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Abdul Rahman on 24/05/2024
 */
abstract class BaseViewModel<in Action : BaseAction, Event : BaseEvent, UiState : BaseUiState>
    (val initialEvent: Event, val initialState: UiState) : ViewModel() {

    private val _event = MutableStateFlow(initialEvent)
    val event: StateFlow<Event> = _event.asStateFlow()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UiState> = _state.asStateFlow()

    protected fun emitEvent(event: Event) {
        _event.value = event
        // reset the last emitted event so that the associated composable doesn't hold the
        // previous event on coming back to the same composable from any other composable
        viewModelScope.launch {
            delay(500)
            _event.value = initialEvent
        }
    }

    protected fun updateState(state: UiState) {
        _state.value = state
    }

    abstract fun processAction(action: Action)
}