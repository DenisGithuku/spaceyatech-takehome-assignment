package com.githukudenis.takehomeassignment.presentation.routes.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    var uiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.OnEmailChange -> {
                uiState.update { prevState -> prevState.copy(email = event.email) }
            }

            is SignInEvent.OnUsernameChange -> {
                uiState.update { prevState -> prevState.copy(username = event.username) }
            }

            is SignInEvent.OnPasswordChange -> {
                uiState.update { prevState -> prevState.copy(password = event.password) }

            }

            is SignInEvent.OnToggleRememberMe -> {
                uiState.update { prevState -> prevState.copy(rememberMe = event.rememberMe) }
            }

            is SignInEvent.OnTogglePassword -> {
                uiState.update { prevState -> prevState.copy(showPassword = event.showPassword) }
            }
        }
    }
}