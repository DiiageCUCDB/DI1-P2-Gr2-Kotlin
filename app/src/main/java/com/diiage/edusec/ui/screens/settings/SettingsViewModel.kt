package com.diiage.edusec.ui.screens.settings

import android.app.Application
import com.diiage.edusec.ui.core.ViewModel
import com.diiage.edusec.ui.core.theme.ThemeState

interface SettingsContracts {
    data class UiState(
        val currentTheme: ThemeState = ThemeState.System,
        val isAccessibilityMode: Boolean = false,
        val isLoading: Boolean = false
    )

    sealed interface UiAction {
        data class ThemeChanged(val theme: ThemeState) : UiAction
        data class AccessibilityModeToggled(val enabled: Boolean) : UiAction
    }

    sealed interface Event {
        object ThemeUpdated : Event
        object AccessibilityModeUpdated : Event
    }
}

class SettingsViewModel(
    application: Application
) : ViewModel<SettingsContracts.UiState>(
    initialState = SettingsContracts.UiState(),
    application = application
) {

    fun handleAction(action: SettingsContracts.UiAction) {
        when (action) {
            is SettingsContracts.UiAction.ThemeChanged -> updateTheme(action.theme)
            is SettingsContracts.UiAction.AccessibilityModeToggled -> updateAccessibilityMode(action.enabled)
        }
    }

    private fun updateTheme(theme: ThemeState) {
        updateState { copy(currentTheme = theme) }
        sendEvent(SettingsContracts.Event.ThemeUpdated)
    }

    private fun updateAccessibilityMode(enabled: Boolean) {
        updateState { copy(isAccessibilityMode = enabled) }
        sendEvent(SettingsContracts.Event.AccessibilityModeUpdated)
    }
}