package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestResultDetail() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = popupBackground
    ) {
        LazyColumn{
            items(50) {
                ListItem(
                    text = { Text("Item $it", color = Color.White) },
                )
            }
        }
    }

}