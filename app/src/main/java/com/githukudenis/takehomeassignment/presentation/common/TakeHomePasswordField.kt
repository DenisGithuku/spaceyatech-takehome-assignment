package com.githukudenis.takehomeassignment.presentation.common

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.githukudenis.takehomeassignment.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeHomePasswordField(
    modifier: Modifier = Modifier,
    value: String,
    showPassword: Boolean,
    onValueChange: (String) -> Unit,
    onTogglePassword: (Boolean) -> Unit,
    context: Context
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(id = R.string.password_placeholder),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.6f
                )
            )
        },
        trailingIcon = {
            IconButton(onClick = { onTogglePassword(!showPassword) }) {
                Icon(
                    imageVector = if (showPassword) Icons.Default.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = context.getString(R.string.string_toggle_password_visibility)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.6f
            ),
        ),
        shape = MaterialTheme.shapes.small,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}