package com.carpick.carpickapp.screen.DetailSpec

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.SimpleSpecBody
import com.carpick.carpickapp.screen.TestResult.fuelTypeName
import com.carpick.carpickapp.screen.TestResult.testCars
import com.carpick.carpickapp.screen.TestResult.transmissionName
import java.text.DecimalFormat

@Composable
fun DetailSpecBody(
    context: Context,
    scrollState: ScrollState,
    specs: List<List<RowDataTypes>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(0.dp, 24.dp)
    ) {
        SimpleSpecBody(context, specs)
    }
}