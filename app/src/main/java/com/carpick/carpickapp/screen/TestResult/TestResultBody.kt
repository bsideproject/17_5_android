package com.carpick.carpickapp.screen.TestResult

import android.content.Context
import androidx.compose.runtime.Composable
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel

@Composable
fun TestResultBody(
    context: Context,
    testResultViewModel: CarPickTestResultViewModel,
    recommendCars: List<RecommendedCar>,
    onPressMoreAtSimpleSpec: (item: RecommendedCar) -> Unit,
    onPressRetest: () -> Unit,
    selectedIdx: Int,
) {
    for(i in 0 until recommendCars.size) {
        TestResultDetail(
            context,
            onPressMoreAtSimpleSpec = {
                onPressMoreAtSimpleSpec(recommendCars[i])
            },
            onPressRetest,
            selectedCar = recommendCars[i],
            specRowDatas = testResultViewModel.setSpecRowDatas(recommendCars[i]),
            tags = recommendCars[i].tags,
            true,
            selectedIdx == recommendCars[i].id
        )
    }
}