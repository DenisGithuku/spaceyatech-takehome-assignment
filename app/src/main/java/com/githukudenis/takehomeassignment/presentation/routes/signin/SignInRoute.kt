package com.githukudenis.takehomeassignment.presentation.routes.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.githukudenis.takehomeassignment.R
import com.githukudenis.takehomeassignment.presentation.common.FormInputLabel
import com.githukudenis.takehomeassignment.presentation.common.ScreenTitle
import com.githukudenis.takehomeassignment.presentation.common.TakeHomePasswordField
import com.githukudenis.takehomeassignment.presentation.common.TakeHomeTextField
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel<SignInViewModel>(), onSignUpInstead: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SignInScreen(uiState = state, onSignUpInstead = onSignUpInstead, onEvent = { event ->
        viewModel.onEvent(event)
    }, onForgotPassword = {})

}

@Composable
private fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    onEvent: (SignInEvent) -> Unit = {},
    onSignUpInstead: () -> Unit,
    onForgotPassword: () -> Unit,
) {
    val context = LocalContext.current
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            ScreenTitle(title = stringResource(id = R.string.sign_in_title))
            Spacer(modifier = Modifier.height(24.dp))

            /* Username input field */
            FormInputLabel(label = stringResource(id = R.string.username_label))
            TakeHomeTextField(
                value = uiState.username,
                onValueChange = { newValue -> onEvent(SignInEvent.OnUsernameChange(newValue)) },
                placeholder = stringResource(id = R.string.username_placeholder)
            )
            Spacer(modifier = Modifier.height(12.dp))

            /* Password input field */
            FormInputLabel(label = stringResource(id = R.string.password_label))
            TakeHomePasswordField(
                value = uiState.password,
                showPassword = uiState.showPassword,
                onValueChange = { newValue -> onEvent(SignInEvent.OnPasswordChange(newValue)) },
                onTogglePassword = { show -> onEvent(SignInEvent.OnTogglePassword(show)) },
                context = context
            )
            Spacer(modifier = Modifier.height(12.dp))

            /* Remember me row */
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(checked = uiState.rememberMe,
                        onCheckedChange = { onEvent(SignInEvent.OnToggleRememberMe(!uiState.rememberMe)) })
                    Text(
                        text = stringResource(id = R.string.remember_me_label),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.6f
                        )
                    )
                }

                Text(
                    modifier = Modifier.clickable { onForgotPassword() },
                    text = stringResource(id = R.string.forgot_password_label),
                    style = MaterialTheme.typography.labelSmall
                )

            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(
                                if (!uiState.formIsValid) R.string.invalid_form else R.string.valid_form
                            )
                        )
                    }
                }) {
                Text(
                    text = stringResource(id = R.string.sign_in_label),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_account_label),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.6f
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(
                    onClick = onSignUpInstead
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up_label),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }
        }
    }
}