package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun TestResultDetail(
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(popupBackground),
    ) {
        BasicSpec()
        SimpleSpec()
    }

}

