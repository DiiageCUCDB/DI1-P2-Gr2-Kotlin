package com.diiage.edusec.ui.screens.login

import com.diiage.edusec.domain.model.LoginResponse
import android.app.Application
import com.diiage.edusec.domain.usecase.LoginService
import com.diiage.edusec.ui.core.Destination
import com.diiage.edusec.ui.core.ViewModel
import org.koin.core.component.inject

interface LoginContracts {
    data class UiState(
        val identifier: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val isButtonEnabled: Boolean = false
    )

    sealed interface UiAction {
        data class IdentifierChanged(val identifier: String) : UiAction
        object LoginClicked : UiAction
    }

    sealed interface Event {
        object NavigateToHome : Event
        data class ShowError(val message: String) : Event
    }
}

class LoginViewModel(
    application: Application
) : ViewModel<LoginContracts.UiState>(
    initialState = LoginContracts.UiState(),
    application = application
) {

    private val loginService: LoginService by inject()

    fun handleAction(action: LoginContracts.UiAction) {
        when (action) {
            is LoginContracts.UiAction.IdentifierChanged -> updateIdentifier(action.identifier)
            LoginContracts.UiAction.LoginClicked -> login()
        }
    }

    private fun updateIdentifier(identifier: String) {
        updateState {
            copy(
                identifier = identifier,
                errorMessage = null,
                isButtonEnabled = loginService.validateIdentifier(identifier) && !isLoading
            )
        }
    }

    private fun login() {
        if (state.value.isLoading) return

        updateState { copy(isLoading = true, errorMessage = null) }

        // First validate locally
        val validationError = loginService.getErrorMessage(state.value.identifier)
        if (validationError != null) {
            updateState {
                copy(
                    isLoading = false,
                    errorMessage = validationError,
                    isButtonEnabled = loginService.validateIdentifier(state.value.identifier)
                )
            }
            return
        }

        // Call API using fetchData
        fetchData(
            source = { loginService.login(state.value.identifier) },
            onResult = {
                onSuccess { response ->
                    updateState {
                        copy(
                            isLoading = false,
                            isButtonEnabled = loginService.validateIdentifier(state.value.identifier)
                        )
                    }
                    sendEvent(Destination.Home)
                }
                onFailure { error ->
                    updateState {
                        copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Erreur de connexion",
                            isButtonEnabled = loginService.validateIdentifier(state.value.identifier)
                        )
                    }
                }
            }
        )
    }
}