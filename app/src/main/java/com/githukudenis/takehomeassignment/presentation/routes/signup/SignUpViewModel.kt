package com.githukudenis.takehomeassignment.presentation.routes.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel: ViewModel() {
    var uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
        private set

    fun onEvent(event: SignUpEvent) {
        when(event) {
            is SignUpEvent.OnEmailChange -> {
                uiState.update { prevState -> prevState.copy(email = event.email) }
            }

            is SignUpEvent.OnUsernameChange -> {
                uiState.update { prevState -> prevState.copy(username = event.username) }
            }

            is SignUpEvent.OnPasswordChange -> {
                uiState.update { prevState -> prevState.copy(password = event.password) }

            }

            is SignUpEvent.OnToggleTerms -> {
                uiState.update { prevState -> prevState.copy(acceptedTerms = event.acceptedTerms) }
            }

            is SignUpEvent.OnTogglePassword -> {
                uiState.update { prevState -> prevState.copy(showPassword = event.showPassword) }
            }
        }
    }
}