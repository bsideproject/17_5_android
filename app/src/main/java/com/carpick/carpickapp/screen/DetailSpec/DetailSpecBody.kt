package com.carpick.carpickapp.screen.DetailSpec

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carpick.carpickapp.screen.TestResult.SimpleSpecBody
import com.carpick.carpickapp.screen.TestResult.testCars

@Composable
fun DetailSpecBody(
    scrollState: ScrollState
) {
    val testSpecs = testCars[0].specs
    val chunkedSpecs = testSpecs.chunked(2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        SimpleSpecBody(chunkedSpecs, 24)
    }
}