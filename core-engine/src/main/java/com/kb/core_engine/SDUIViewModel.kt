package com.kb.core_engine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * MVI State for the SDUI Screen
 */
data class SDUIState(
    val isLoading: Boolean = false,
    val screen: SDUIScreen? = null,
    val error: String? = null,
    val formData: Map<String, String> = emptyMap()
)

/**
 * MVI Intents
 */
sealed class SDUIIntent {
    data object LoadInitialScreen : SDUIIntent()
    data class LoadScreen(val screenId: String) : SDUIIntent()
    data class HandleAction(val action: SDUIAction) : SDUIIntent()
    data class UpdateField(val key: String, val value: String) : SDUIIntent()
    data class SetLoading(val screenId: String) : SDUIIntent()
    data class ScreenLoaded(val screen: SDUIScreen) : SDUIIntent()
    data class ScreenLoadFailed(val message: String) : SDUIIntent()
}

class SDUIViewModel(
    private val repository: ScreenRepository = MockScreenRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SDUIState())
    val uiState: StateFlow<SDUIState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<String>()
    val sideEffect: SharedFlow<String> = _sideEffect.asSharedFlow()

    fun processIntent(intent: SDUIIntent) {
        when (intent) {
            SDUIIntent.LoadInitialScreen -> loadInitialScreen()
            is SDUIIntent.LoadScreen -> loadScreen(intent.screenId)
            is SDUIIntent.HandleAction -> handleAction(intent.action)
            is SDUIIntent.UpdateField -> dispatch(intent)
            is SDUIIntent.SetLoading -> dispatch(intent)
            is SDUIIntent.ScreenLoaded -> dispatch(intent)
            is SDUIIntent.ScreenLoadFailed -> dispatch(intent)
        }
    }

    private fun loadInitialScreen() {
        viewModelScope.launch {
            try {
                val initial = repository.fetchInitialScreenId()
                processIntent(SDUIIntent.LoadScreen(initial))
            } catch (e: Exception) {
                dispatch(SDUIIntent.ScreenLoadFailed(e.message ?: "Unable to load initial screen"))
            }
        }
    }

    private fun dispatch(intent: SDUIIntent) {
        _uiState.update { current -> SDUIReducer.reduce(current, intent) }
    }

    private fun loadScreen(screenId: String) {
        viewModelScope.launch {
            dispatch(SDUIIntent.SetLoading(screenId))
            try {
                val response = repository.fetchScreen(ScreenRequest(screenId = screenId))
                val screen = SDUIJsonParser.instance.decodeFromString<SDUIScreen>(response.payload)
                dispatch(SDUIIntent.ScreenLoaded(screen))
                screen.onLoadActions?.forEach { handleAction(it) }
            } catch (e: Exception) {
                dispatch(SDUIIntent.ScreenLoadFailed(e.message ?: "Unable to load screen"))
            }
        }
    }

    private fun handleAction(action: SDUIAction) {
        when (action) {
            is SDUIAction.Navigate -> {
                processIntent(SDUIIntent.LoadScreen(action.destination))
            }
            is SDUIAction.ApiCall -> {
                if (action.url == "/api/login") {
                    val user = uiState.value.formData["user"]?.trim()
                    val pass = uiState.value.formData["pass"]?.trim()
                    
                    if (user.isNullOrBlank() || pass.isNullOrBlank()) {
                        viewModelScope.launch { _sideEffect.emit("Username and password cannot be empty") }
                    } else if (user == "kalyan" && pass == "12345") {
                        action.onSuccess?.let { handleAction(it) }
                    } else {

                        action.onFailure?.let { handleAction(it) }
                    }
                } else {

                    action.onSuccess?.let { handleAction(it) }
                }
            }
            is SDUIAction.ShowToast -> {
                viewModelScope.launch { _sideEffect.emit(action.message) }
            }
            is SDUIAction.OpenUrl -> {
                println("Opening URL: ${action.url}")
            }
        }
    }
}
