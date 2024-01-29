package com.githukudenis.takehomeassignment.presentation.routes.signup

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val acceptedTerms: Boolean = false,
    val showPassword: Boolean = false,
) {
    val formIsValid: Boolean get() =
        username.isNotEmpty() && email.isNotEmpty() && email.contains('@') &&
                password.isNotEmpty() && password.length >= 6 && acceptedTerms
}

sealed class SignUpEvent {
    data class OnUsernameChange(val username: String) : SignUpEvent()
    data class OnEmailChange(val email: String) : SignUpEvent()
    data class OnToggleTerms(val acceptedTerms: Boolean) : SignUpEvent()
    data class OnPasswordChange(val password: String) : SignUpEvent()
    data class OnTogglePassword(val showPassword: Boolean) : SignUpEvent()
}