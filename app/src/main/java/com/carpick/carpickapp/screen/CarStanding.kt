package com.carpick.carpickapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CarStanding() {
    CarStandingView()
}

@Composable
fun CarStandingView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(text = "자동차 순위표")
    }
}

@Preview(showBackground = true)
@Composable
fun CarStandingPreview() {
    CarStandingView()
}