package com.githukudenis.takehomeassignment.presentation.routes.signin

data class SignInUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val showPassword: Boolean = false,
) {
    val formIsValid: Boolean
        get() = username.isNotEmpty() && password.isNotEmpty() && rememberMe && password.length >= 6
}

sealed class SignInEvent {
    data class OnUsernameChange(val username: String) : SignInEvent()
    data class OnEmailChange(val email: String) : SignInEvent()
    data class OnToggleRememberMe(val rememberMe: Boolean) : SignInEvent()
    data class OnPasswordChange(val password: String) : SignInEvent()
    data class OnTogglePassword(val showPassword: Boolean) : SignInEvent()
}