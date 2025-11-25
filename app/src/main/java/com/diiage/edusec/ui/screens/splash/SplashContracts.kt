package com.diiage.edusec.ui.screens.splash

interface SplashContracts {
    data class UiState(
        val isLoading: Boolean = true
    )

    sealed interface UiAction {
        object SplashCompleted : UiAction
    }

    sealed interface Event {
        object NavigateTo : Event
    }
}