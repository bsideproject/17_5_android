package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TestResultFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(1.dp, color = Color.Red)
    ) {

    }
}