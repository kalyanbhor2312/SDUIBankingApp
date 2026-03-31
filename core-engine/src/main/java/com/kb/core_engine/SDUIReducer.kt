package com.kb.core_engine

object SDUIReducer {
    fun reduce(state: SDUIState, intent: SDUIIntent): SDUIState {
        return when (intent) {
            is SDUIIntent.SetLoading -> state.copy(
                isLoading = true,
                error = null,
                formData = emptyMap()
            )

            is SDUIIntent.ScreenLoaded -> state.copy(
                isLoading = false,
                screen = intent.screen,
                error = null
            )

            is SDUIIntent.ScreenLoadFailed -> state.copy(
                isLoading = false,
                error = intent.message
            )

            is SDUIIntent.UpdateField -> state.copy(
                formData = state.formData + (intent.key to intent.value)
            )

            is SDUIIntent.LoadScreen,
            SDUIIntent.LoadInitialScreen,
            is SDUIIntent.HandleAction -> state
        }
    }
}

