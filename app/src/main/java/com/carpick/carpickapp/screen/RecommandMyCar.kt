package com.carpick.carpickapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecommandMyCar() {
    RecommandMyCarView()
}

@Composable
fun RecommandMyCarView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Text(text = "내 차 추천")
    }
}

@Preview(showBackground = true)
@Composable
fun RecommandMyCarPreview() {
    RecommandMyCarView()
}