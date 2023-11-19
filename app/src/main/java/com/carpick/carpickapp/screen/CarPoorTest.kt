package com.carpick.carpickapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CarPoorTest() {
    CarPoorTestView()
}

@Composable
fun CarPoorTestView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(text = "카푸어 테스트")
    }
}

@Preview(showBackground = true)
@Composable
fun CarPoorTestPreview() {
    CarPoorTestView()
}