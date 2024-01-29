package com.githukudenis.takehomeassignment.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScreenTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun ScreenTitlePreview() {
    ScreenTitle(title = "Create account")
}